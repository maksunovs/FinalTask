package com.epam.final_task.controller.command.track.client;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.OrderService;
import com.epam.final_task.service.OrderTrackService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AddTrackToCartCommand implements Command {

    private static final String TRACK_ID_PARAMETER = "track_id";

    private static final String USER_ATTRIBUTE = "user";

    private static final String REFERER = "referer";

    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute(USER_ATTRIBUTE);
        int trackId = Integer.parseInt(request.getParameter(TRACK_ID_PARAMETER));
        ServiceFactory factory = new ServiceFactory();
        OrderService orderService = factory.getOrderService();
        TrackService trackService = factory.getTrackService();
        Optional<Track> track = trackService.findById(trackId);
        Optional<Order> order = orderService.findByUserId(client.getId());
        OrderTrackService orderTrackService = factory.getOrderTrackService();
        if (order.isPresent() && track.isPresent()) {
            List<Track> purchasedTracks = trackService.findPurchasedTracks(client.getId());
            List<Track> orderedTracks = trackService.findOrderedTracks(order.get().getId());
            if (!purchasedTracks.contains(track.get()) && !orderedTracks.contains(track.get())) {
                orderTrackService.save(new OrderTrack(order.get().getId(), trackId));

            }
        }
        return new ResponseContent(ResponseType.REDIRECT, request.getHeader(REFERER));
    }
}
