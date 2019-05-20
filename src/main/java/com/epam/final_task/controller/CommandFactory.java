package com.epam.final_task.controller;

import com.epam.final_task.controller.command.*;
import com.epam.final_task.controller.command.album.*;
import com.epam.final_task.controller.command.album.admin.DeleteAlbumCommand;
import com.epam.final_task.controller.command.album.admin.EditAlbumCommand;
import com.epam.final_task.controller.command.album.admin.SaveAlbumCommand;
import com.epam.final_task.controller.command.artist.*;
import com.epam.final_task.controller.command.artist.admin.AddArtistCommand;
import com.epam.final_task.controller.command.artist.admin.DeleteArtistCommand;
import com.epam.final_task.controller.command.artist.admin.SaveArtistCommand;
import com.epam.final_task.controller.command.track.client.AddTrackToCartCommand;
import com.epam.final_task.controller.command.cart.PayOrderCommand;
import com.epam.final_task.controller.command.cart.ShowCartCommand;
import com.epam.final_task.controller.command.client.TopUpBalanceCommand;
import com.epam.final_task.controller.command.client.TopUpBalancePageCommand;
import com.epam.final_task.controller.command.playlist.*;
import com.epam.final_task.controller.command.playlist.admin.DeletePlaylistCommand;
import com.epam.final_task.controller.command.playlist.admin.EditPlaylistCommand;
import com.epam.final_task.controller.command.playlist.admin.SavePlaylistCommand;
import com.epam.final_task.controller.command.track.admin.*;
import com.epam.final_task.controller.command.track.client.BuyTrackCommand;
import com.epam.final_task.controller.command.track.client.RemoveTrackFromCartCommand;
import com.epam.final_task.controller.command.track.client.ShowPurchasedTracksCommand;
import com.epam.final_task.util.DataValidator;
import com.epam.final_task.service.helper.TrackStateInitializer;

public class CommandFactory {
    public static Command getCommand(String commandName) {
        Command command = null;
        switch (commandName) {
            case "home":
                command = new ShowHomePageCommand(new TrackStateInitializer());
                break;
            case "login":
                command = new LoginCommand();
                break;
            case "add_audiotrack":
                command = new AddTrackCommand();
                break;
            case "delete_audiotrack":
                command = new DeleteTrackCommand();
                break;
            case "save_audiotrack":
                command = new SaveTrackCommand(new DataValidator());
                break;
            case "view_artists":
                command = new ViewArtistsListCommand();
                break;
            case "view_artist":
                command = new ViewArtistCommand(new TrackStateInitializer());
                break;
            case "add_artist":
                command = new AddArtistCommand();
                break;
            case "save_artist":
                command = new SaveArtistCommand(new DataValidator());
                break;
            case "delete_artist":
                command = new DeleteArtistCommand();
                break;
            case "view_playlists":
                command = new ViewPlaylistsListCommand();
                break;
            case "view_playlist":
                command = new ViewPlaylistCommand(new TrackStateInitializer());
                break;
            case "save_playlist":
                command = new SavePlaylistCommand(new DataValidator());
                break;
            case "delete_playlist":
                command = new DeletePlaylistCommand();
                break;
            case "remove_audiotrack_from_playlist":
                command = new RemoveTrackFromPlaylistCommand();
                break;
            case "add_tracks_to_playlist":
                command = new EditPlaylistCommand();
                break;
            case "add_track_to_playlist":
                command = new AddTrackToPlaylistCommand();
                break;
            case "save_album":
                command = new SaveAlbumCommand(new DataValidator());
                break;
            case "view_albums":
                command = new ViewAlbumsListCommand();
                break;
            case "view_album":
                command = new ViewAlbumCommand(new TrackStateInitializer());
                break;
            case "edit_album":
                command = new EditAlbumCommand();
                break;
            case "delete_album":
                command = new DeleteAlbumCommand();
                break;
            case "add_track_to_album":
                command = new AddTrackToAlbumCommand();
                break;
            case "remove_track_from_album":
                command = new RemoveTrackFromAlbumCommand();
                break;
            case "logout":
                command = new LogoutCommand();
                break;
            case "purchases":
                command = new ShowPurchasedTracksCommand();
                break;
            case "cart":
                command = new ShowCartCommand();
                break;
            case "add_to_cart":
                command = new AddTrackToCartCommand();
                break;
            case "remove_from_cart":
                command = new RemoveTrackFromCartCommand();
                break;
            case "pay_order":
                command = new PayOrderCommand();
                break;
            case "top_up_balance_page":
                command = new TopUpBalancePageCommand();
                break;
            case "top_up_balance":
                command = new TopUpBalanceCommand(new DataValidator());
                break;
            case "buy_track":
                command = new BuyTrackCommand();
                break;
            case "change_language":
                command = new ChangeLanguageCommand();
                break;
            case "change_currency":
                command = new ChangeCurrencyCommand();
                break;
        }
        return command;
    }
}
