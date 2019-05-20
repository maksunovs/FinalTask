package com.epam.final_task.controller.command.client;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopUpBalancePageCommand implements Command {

    private static final String CONTENT_PATH = "WEB-INF/view/top_up_balance.jsp";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        return new ResponseContent(ResponseType.FORWARD, CONTENT_PATH);
    }
}
