package com.eu.habbo.messages.incoming.room.session;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.session.CloseConnectionComposer;

public class QuitEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.getHabbo().getHabboInfo().setLoadingRoom(0);

        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null) {
            Emulator.getGameEnvironment().getRoomManager().leaveRoom(this.client.getHabbo(), this.client.getHabbo().getHabboInfo().getCurrentRoom());
        }

        if (this.client.getHabbo().getHabboInfo().getRoomQueueId() != 0) {
            Room room = Emulator.getGameEnvironment().getRoomManager().getRoom(this.client.getHabbo().getHabboInfo().getRoomQueueId());

            if (room != null) {
                room.removeFromQueue(this.client.getHabbo());
            } else {
                this.client.getHabbo().getHabboInfo().setRoomQueueId(0);
            }
            this.client.sendResponse(new CloseConnectionComposer());
        }

        if (this.client.getHabbo().getRoomUnit() != null) {
            this.client.getHabbo().getRoomUnit().clearWalking();
            this.client.getHabbo().getRoomUnit().setInRoom(false);
        }
    }
}
