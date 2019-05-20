package com.epam.final_task.controller;

import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.dao.connection.ConnectionPool;
import com.epam.final_task.model.dao.exception.ConnectionException;
import com.epam.final_task.model.entity.ResponseType;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private static final String COMMAND_PARAMETER ="command";

    private static final int NOT_FOUND_ERROR = 404;
    private static final int SERVER_ERROR = 500;

    private static final Logger LOGGER = Logger.getLogger(FrontController.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(COMMAND_PARAMETER);
        if (commandName != null) {
            Command command = CommandFactory.getCommand(commandName);
            try {
                ResponseContent responseContent = command.execute(request, response);
                if (responseContent.getResponseType() == ResponseType.FORWARD) {
                    request.getRequestDispatcher(responseContent.getContentPath()).forward(request, response);
                } else {
                    response.sendRedirect(responseContent.getContentPath());
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                response.sendError(SERVER_ERROR);
            }
        } else {
            response.sendError(NOT_FOUND_ERROR);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().closeAll();
        } catch (ConnectionException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
