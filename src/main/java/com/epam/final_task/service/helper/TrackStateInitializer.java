package com.epam.final_task.service.helper;

import com.epam.final_task.model.entity.*;
import com.epam.final_task.model.entity.enums.TrackState;
import com.epam.final_task.service.CartService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;
import com.epam.final_task.service.impl.CartServiceImpl;

import java.util.List;
import java.util.Optional;

public class TrackStateInitializer {

    public void initializeStates(List<Track> tracks, Client client) throws ServiceException {
        CartService cartService = new CartServiceImpl();
        Optional<Cart> order = cartService.findByUserId(client.getId());
        if (!order.isPresent()) {
            throw new ServiceException("Cart is not exist");
        }
        ServiceFactory factory = new ServiceFactory();
        TrackService trackService = factory.getTrackService();
        List<Track> purchasedTracks = trackService.findPurchasedTracks(client.getId());
        List<Track> orderedTracks = trackService.findTracksInCart(order.get().getId());
        for (Track track : tracks) {
            if (purchasedTracks.contains(track)) {
                track.setState(TrackState.PURCHASED);
            }
        }
        for (Track track : tracks) {
            if (orderedTracks.contains(track)) {
                track.setState(TrackState.IN_CART);
            }
        }
    }
}
