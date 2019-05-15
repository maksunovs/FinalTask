package com.epam.final_task.controller.command.client;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Client;
import com.epam.final_task.model.entity.Currency;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.UserService;
import com.epam.final_task.service.implementaiton.UserServiceImpl;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class TopUpBalanceCommand implements Command {

    private static final String CONTENT_PATH = "music?command=top_up_balance_page";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        String stringAmount = request.getParameter("amount");
        BigDecimal amount = new BigDecimal(stringAmount);
        Client client = (Client) session.getAttribute("user");
        BigDecimal oldCash = client.getCash();
        BigDecimal newCash = oldCash.add(amount);
        UserService service = new UserServiceImpl();
        service.updateCash(newCash, client.getId());
        client.setCash(newCash);
        return new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
    }
}
