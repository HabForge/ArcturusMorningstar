package com.eu.habbo.messages.incoming.room.engine;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;

public class RemoveBotFromFlatEvent extends MessageHandler {


    @Override
    public void handle() throws Exception {
        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();

        if (room == null)
            return;

        Emulator.getGameEnvironment().getBotManager().pickUpBot(this.packet.readInt(), this.client.getHabbo());
    }
}
