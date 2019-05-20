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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AuthorizationFilter implements Filter {

    private final static List<String> ADMIN_COMMANDS = Arrays.asList("home",
            "login",
            "logout",
            "add_audiotrack",
            "delete_audiotrack",
            "save_audiotrack",
            "view_artists",
            "view_artist",
            "save_artist",
            "add_artist",
            "delete_artist",
            "view_playlists",
            "view_playlist",
            "save_playlist",
            "delete_playlist",
            "remove_audiotrack_from_playlist",
            "add_tracks_to_playlist",
            "add_track_to_playlist",
            "save_album",
            "view_album",
            "edit_album",
            "view_albums",
            "delete_album",
            "add_track_to_album",
            "remove_track_from_album",
            "change_language");
    private final static List<String> CLIENT_COMMANDS = Arrays.asList("home",
            "login",
            "logout",
            "view_artists",
            "view_artist",
            "view_playlists",
            "view_playlist",
            "view_album",
            "view_albums",
            "purchases",
            "cart",
            "add_to_cart",
            "pay_order",
            "top_up_balance",
            "top_up_balance_page",
            "buy_track",
            "remove_from_cart",
            "change_currency",
            "change_language");
    private final static List<String> IGNORED_COMMANDS = Arrays.asList("login",
            "change_language");


    private static final String COMMAND_PARAMETER = "command";

    private static final String USER_ATTRIBUTE = "user";

    private static final String INDEX_PAGE = "index.jsp";

    private static final int NOT_FOUND_ERROR = 404;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        String command = request.getParameter(COMMAND_PARAMETER);

        boolean withError = false;

        if (user == null) {
            if (!IGNORED_COMMANDS.contains(command)) {
                request.getRequestDispatcher(INDEX_PAGE).forward(servletRequest, servletResponse);
                withError = true;
            }
        } else {
            switch (user.getRole()) {
                case CLIENT:
                    if (!CLIENT_COMMANDS.contains(command)) {
                        ((HttpServletResponse) servletResponse).sendError(NOT_FOUND_ERROR);
                        withError = true;
                    }
                    break;

                case ADMIN:
                    if (!ADMIN_COMMANDS.contains(command)) {
                        ((HttpServletResponse) servletResponse).sendError(NOT_FOUND_ERROR);
                        withError = true;
                    }
                    break;
            }

        }
        if (!withError) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
