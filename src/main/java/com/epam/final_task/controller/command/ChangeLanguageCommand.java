package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.model.entity.Language;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String language = request.getParameter("language");
        Language newLanguage = Language.valueOf(language);
        HttpSession session = request.getSession();
        session.setAttribute("language", newLanguage);
        return new ResponseContent(ResponseType.REDIRECT, request.getHeader(REFERER));
    }
}
