package com.epam.final_task.controller.command.track.client;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Order;
import com.epam.final_task.model.entity.OrderTrack;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.OrderService;
import com.epam.final_task.service.OrderTrackService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class RemoveTrackFromCartCommand implements Command {

    private static final String TRACK_ID_PARAMETER = "track_id";

    private static final String USER_ATTRIBUTE = "user";

    private static final String CONTENT_PATH = "music?command=cart";
    private static final String HOME = "music?command=home";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        int trackId = Integer.parseInt(request.getParameter(TRACK_ID_PARAMETER));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        ServiceFactory factory = new ServiceFactory();
        OrderService orderService = factory.getOrderService();
        Optional<Order> order = orderService.findByUserId(user.getId());
        ResponseContent responseContent;
        if (order.isPresent()) {
            OrderTrackService orderTrackService = factory.getOrderTrackService();
            Optional<OrderTrack> orderTrack = orderTrackService.findByOrderIdAndTrackId(order.get().getId(), trackId);
            if (orderTrack.isPresent()) {
                orderTrackService.removeById(orderTrack.get().getId());
            }
            responseContent = new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, HOME);
        }
        return responseContent;
    }
}
