package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.UserService;
import com.epam.final_task.service.implementaiton.UserServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String CONTENT_PATH ="/music?command=home";
    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService service = new UserServiceImpl();
        Optional<User> user = service.login(login, password);
        ResponseContent responseContent;
        if (user.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("user",user.get());
            responseContent = new ResponseContent(ResponseType.REDIRECT,CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT,"/");
        }
        return  responseContent;
    }
}
