package com.eu.habbo.messages.incoming.room.furniture;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.rooms.items.MoodLightDataComposer;

public class RoomDimmerGetPresetsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null)
            this.client.sendResponse(new MoodLightDataComposer(this.client.getHabbo().getHabboInfo().getCurrentRoom().getMoodlightData()));
    }
}
