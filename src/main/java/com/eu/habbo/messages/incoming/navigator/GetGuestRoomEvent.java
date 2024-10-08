package com.eu.habbo.messages.incoming.navigator;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.navigator.GetGuestRoomResultComposer;

public class GetGuestRoomEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Room room = Emulator.getGameEnvironment().getRoomManager().loadRoom(this.packet.readInt());

        int something = this.packet.readInt();
        int something2 = this.packet.readInt();
        if (room != null) {
            boolean unknown = true;

            if (something == 0 && something2 == 1) {
                unknown = false;
            }

            //this.client.getHabbo().getHabboInfo().getCurrentRoom() != room
            this.client.sendResponse(new GetGuestRoomResultComposer(room, this.client.getHabbo(), true, unknown));
        }
    }
}
