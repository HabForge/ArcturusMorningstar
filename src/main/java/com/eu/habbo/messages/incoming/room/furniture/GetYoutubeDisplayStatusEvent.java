package com.eu.habbo.messages.incoming.room.furniture;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.items.YoutubeManager;
import com.eu.habbo.habbohotel.items.interactions.InteractionYoutubeTV;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.error.ErrorReportComposer;
import com.eu.habbo.messages.outgoing.room.furniture.YoutubeDisplayPlaylistsComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class GetYoutubeDisplayStatusEvent extends MessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetYoutubeDisplayStatusEvent.class);

    @Override
    public void handle() throws Exception {
        int itemId = this.packet.readInt();

        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null) {
            HabboItem item = this.client.getHabbo().getHabboInfo().getCurrentRoom().getHabboItem(itemId);

            if (item instanceof InteractionYoutubeTV) {
                InteractionYoutubeTV tv = (InteractionYoutubeTV) item;

                ArrayList<YoutubeManager.YoutubePlaylist> playlists = Emulator.getGameEnvironment().getItemManager().getYoutubeManager().getPlaylistsForItemId(item.getBaseItem().getId());

                if (playlists == null) {
                    LOGGER.error("No YouTube playlists set for base item #" + item.getBaseItem().getId());
                    this.client.sendResponse(new ErrorReportComposer(1000));
                    return;
                }

                this.client.sendResponse(new YoutubeDisplayPlaylistsComposer(itemId, playlists, tv.currentPlaylist));
            }
        }
    }
}