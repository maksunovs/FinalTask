package com.epam.final_task.controller.command.client;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Client;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.UserService;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.util.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class TopUpBalanceCommand implements Command {

    private static final String AMOUNT_PARAMETER = "amount";

    private static final String USER_ATTRIBUTE = "user";

    private static final String CONTENT_PATH = "music?command=top_up_balance_page";

    private final DataValidator validator;

    public TopUpBalanceCommand(DataValidator validator) {
        this.validator = validator;
    }

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        String stringAmount = request.getParameter(AMOUNT_PARAMETER);
        Client client = (Client) session.getAttribute(USER_ATTRIBUTE);
        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.getUserService();
        if (validator.validateValue(stringAmount)) {
            BigDecimal amount = new BigDecimal(stringAmount);
            BigDecimal oldCash = service.findCash(client.getId());
            BigDecimal newCash = oldCash.add(amount);
            service.updateCash(newCash, client.getId());
            client.setCash(newCash);
        }
        return new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
    }
}
