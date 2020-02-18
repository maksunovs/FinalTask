package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.model.entity.enums.ResponseType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {

    private static final String CONTENT_PATH = "/";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseContent(ResponseType.REDIRECT,CONTENT_PATH);
    }
}
