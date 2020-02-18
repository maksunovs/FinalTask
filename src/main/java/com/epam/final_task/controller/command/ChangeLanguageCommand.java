package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.model.entity.enums.Language;
import com.epam.final_task.model.entity.enums.ResponseType;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {

    private static final String LANGUAGE_PARAMETER = "language";

    private static final String LANGUAGE_ATTRIBUTE = "language";

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String language = request.getParameter(LANGUAGE_PARAMETER);
        Language newLanguage = Language.valueOf(language);
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE, newLanguage);
        return new ResponseContent(ResponseType.REDIRECT, request.getHeader(REFERER));
    }
}
