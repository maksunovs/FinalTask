package com.epam.final_task.model.dao.connection;

import com.epam.final_task.model.dao.exception.ConnectionException;
import com.epam.final_task.util.PropertyLoader;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static final Lock LOCKER = new ReentrantLock();
    private static final int POOL_SIZE = 10;
    private static final Semaphore SEMAPHORE = new Semaphore(POOL_SIZE);

    private static volatile ConnectionPool instance;
    private final Queue<Connection> pool = new ArrayDeque<>();

    private ConnectionPool() {
        init();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            LOCKER.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                LOCKER.unlock();
            }
        }
        return instance;
    }

    private void init() {
        ConnectionFactory factory = new ConnectionFactory(new PropertyLoader());
        for (int i = 0; i < 10; i++) {
            Connection connection = factory.createConnection();
            pool.add(connection);
        }
    }

    public Connection getConnection() {
        try {
            SEMAPHORE.acquire();
            LOCKER.lock();
            return pool.poll();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ConnectionException("Failed to get connection", e);
        } finally {
            LOCKER.unlock();
        }
    }

    public void returnConnection(Connection connection) {
        try {
            LOCKER.lock();
            pool.add(connection);
        } finally {
            LOCKER.unlock();
            SEMAPHORE.release();
        }
    }

    public void closeAll() {
        try {
            LOCKER.lock();
            try {
                for (Connection connection : pool) {
                    connection.close();
                    pool.remove();
                }
            } catch (SQLException e) {
                throw new ConnectionException("Failed to close connection", e);
            }
        } finally {
            LOCKER.unlock();
        }
    }
}
