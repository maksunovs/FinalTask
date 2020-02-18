package com.epam.final_task.controller.command;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.model.entity.enums.Currency;
import com.epam.final_task.model.entity.enums.ResponseType;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeCurrencyCommand implements Command {

    private static final String CURRENCY_PARAMETER = "currency";

    private static final String CURRENCY_ATTRIBUTE = "currency";

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        String currency = request.getParameter(CURRENCY_PARAMETER);
        Currency newCurrency = Currency.valueOf(currency);
        HttpSession session = request.getSession();
        session.setAttribute(CURRENCY_ATTRIBUTE, newCurrency);
        return new ResponseContent(ResponseType.REDIRECT, request.getHeader(REFERER));
    }
}
