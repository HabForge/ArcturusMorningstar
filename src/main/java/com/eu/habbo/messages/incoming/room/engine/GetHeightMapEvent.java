package com.eu.habbo.messages.incoming.room.engine;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.engine.FloorHeightMapComposer;
import com.eu.habbo.messages.outgoing.room.engine.HeightMapComposer;

public class GetHeightMapEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().getHabboInfo().getLoadingRoom() > 0) {
            Room room = Emulator.getGameEnvironment().getRoomManager().loadRoom(this.client.getHabbo().getHabboInfo().getLoadingRoom());

            if (room != null && room.getLayout() != null) {
                this.client.sendResponse(new HeightMapComposer(room));

                this.client.sendResponse(new FloorHeightMapComposer(room));

                Emulator.getGameEnvironment().getRoomManager().enterRoom(this.client.getHabbo(), room);
            }
        }
    }
}
