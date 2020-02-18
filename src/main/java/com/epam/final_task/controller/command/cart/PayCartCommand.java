package com.epam.final_task.controller.command.cart;

import com.epam.final_task.controller.ResponseContent;
import com.epam.final_task.controller.command.Command;
import com.epam.final_task.model.entity.Cart;
import com.epam.final_task.model.entity.enums.ResponseType;
import com.epam.final_task.model.entity.Track;
import com.epam.final_task.model.entity.User;
import com.epam.final_task.service.CartService;
import com.epam.final_task.service.ServiceFactory;
import com.epam.final_task.service.TrackService;
import com.epam.final_task.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PayCartCommand implements Command {

    private static final String USER_ATTRIBUTE = "user";

    private static final String HOME_PAGE = "music?command=home";

    private static final String ZERO_PARAMETER = "0";

    private static final String CONTENT_PATH = "music?command=cart";


    @Override
    public ResponseContent execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        HttpSession session = request.getSession();
        ServiceFactory factory = new ServiceFactory();
        CartService cartService = factory.getCartService();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        Optional<Cart> cart = cartService.findByUserId(user.getId());
        ResponseContent responseContent;
        if (cart.isPresent()) {
            TrackService trackService = factory.getTrackService();
            List<Track> tracks = trackService.findTracksInCart(cart.get().getId());
            BigDecimal value = cartValue(tracks);
            cartService.payCart(user, cart.get().getId(), value);
            responseContent = new ResponseContent(ResponseType.REDIRECT, CONTENT_PATH);
        } else {
            responseContent = new ResponseContent(ResponseType.REDIRECT, HOME_PAGE);
        }
        return responseContent;

    }

    private BigDecimal cartValue(List<Track> tracks) {
        BigDecimal value = new BigDecimal(ZERO_PARAMETER);
        for (Track track : tracks) {
            value = value.add(track.getPrice());
        }
        return value;
    }
}
