package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.model.entity.enums.ResponseType;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.UserService;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";

    private static final String USER_ATTRIBUTE = "user";
    private static final String CONTENT_PATH = "music?command=home";
    private static final String INDEX_WITH_LOGIN_ERROR = "/?login=error.login";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        ResponseContent responseContent;
        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.getUserService();
        Optional<User> user = service.login(login, password);
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute(USER_ATTRIBUTE, user.get());
            responseContent = new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, INDEX_WITH_LOGIN_ERROR);
        }
        return responseContent;
    }
}
