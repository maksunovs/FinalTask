package com.epam.final_task.util;

import com.epam.final_task.model.entity.*;
import com.epam.final_task.service.OrderService;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.service.implementaiton.OrderServiceImpl;
import com.epam.final_task.service.implementaiton.TrackServiceImpl;

import java.util.List;
import java.util.Optional;

public class TrackStateInitializer {

    public void initializeStates(List<Track> tracks, Client client) throws ServiceException {
        OrderService orderService = new OrderServiceImpl();
        Optional<Order> order = orderService.findByUserId(client.getId());
        if(!order.isPresent()){
            throw  new ServiceException("Cart is not exist");
        }
        TrackService trackService = new TrackServiceImpl();
        List<Track> purchasedTracks = trackService.findPurchasedTracks(client.getId());
        List<Track> orderedTracks = trackService.findOrderedTracks(order.get().getId());
        for (Track track : tracks) {
            if (purchasedTracks.contains(track)) {
                track.setState(TrackState.PURCHASED);
            }
        }
        for (Track track : tracks) {
            if (orderedTracks.contains(track)) {
                track.setState(TrackState.ORDERED);
            }
        }
    }
}
