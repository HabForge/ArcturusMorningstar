package com.eu.habbo.messages.incoming.vault;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.navigator.PrivateRoomsComposer;

public class SearchRoomsByTagEventEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        String tag = this.packet.readString();

        this.client.sendResponse(new PrivateRoomsComposer(Emulator.getGameEnvironment().getRoomManager().getRoomsWithTag(tag)));
    }
}
