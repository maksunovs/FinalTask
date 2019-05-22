package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException;
}
