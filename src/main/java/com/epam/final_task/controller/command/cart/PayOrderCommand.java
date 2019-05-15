package com.epam.final_task.controller.command.cart;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Order;
import com.epam.final_task.model.entity.ResponseType;
import com.epam.final_task.model.entity.Track;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.OrderService;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.implementaiton.OrderServiceImpl;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.service.implementaiton.TrackServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PayOrderCommand implements Command {

    private static final String CONTENT_PATH = "music?command=cart";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        BigDecimal value = new BigDecimal("0");
        OrderService orderService = new OrderServiceImpl();
        User user = (User)session.getAttribute("user");
        Optional<Order> order = orderService.findByUserId(user.getId());
        ResponseContent responseContent;
        if (order.isPresent()) {
            TrackService trackService = new TrackServiceImpl();
            List<Track> tracks = trackService.findOrderedTracks(order.get().getId());
            System.out.println(tracks.size());
            for(Track track: tracks){
                value = value.add(track.getPrice());
            }
            System.out.println(value.toString());
            orderService.payOrder(user,order.get().getId(),value);
            responseContent = new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, "music?command=home");
        }
        return responseContent;
    }
}
