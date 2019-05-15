package com.epam.final_task.filter;

import com.epam.final_task.model.entity.AdminCommand;
import com.epam.final_task.model.entity.ClientCommand;
import com.epam.final_task.model.entity.IgnoredCommand;
import com.epam.final_task.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private static final int NOT_FOUND_ERROR = 404;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String command = request.getParameter("command");
        if (user == null) {
            try{
                IgnoredCommand.valueOf(command.toUpperCase());
                filterChain.doFilter(servletRequest,servletResponse);
            }catch (IllegalArgumentException e){
                request.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            switch (user.getRole()) {
                case CLIENT:
                    try {
                        ClientCommand.valueOf(command.toUpperCase());
                        filterChain.doFilter(servletRequest, servletResponse);
                    } catch (IllegalArgumentException e) {
                        ((HttpServletResponse)servletResponse).sendError(NOT_FOUND_ERROR);
                    }
                    break;
                case ADMIN:
                    try {
                        AdminCommand.valueOf(command.toUpperCase());
                        filterChain.doFilter(servletRequest, servletResponse);
                    } catch (IllegalArgumentException e) {
                        ((HttpServletResponse)servletResponse).sendError(NOT_FOUND_ERROR);
                    }
                    break;
            }
        }
    }
}
