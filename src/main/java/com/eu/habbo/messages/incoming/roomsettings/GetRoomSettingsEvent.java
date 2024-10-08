package com.eu.habbo.messages.incoming.roomsettings;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.roomsettings.RoomSettingsDataComposer;

public class GetRoomSettingsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int roomId = this.packet.readInt();

        Room room = Emulator.getGameEnvironment().getRoomManager().getRoom(roomId);

        if (room != null)
            this.client.sendResponse(new RoomSettingsDataComposer(room));
    }
}
