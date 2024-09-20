package com.eu.habbo.messages.incoming.room.layout;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.layout.RoomEntryTileComposer;

public class GetRoomEntryTileEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() == null)
            return;

        this.client.sendResponse(new RoomEntryTileComposer(this.client.getHabbo().getHabboInfo().getCurrentRoom()));
    }
}
