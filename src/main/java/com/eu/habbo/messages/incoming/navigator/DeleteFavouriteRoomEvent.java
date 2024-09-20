package com.eu.habbo.messages.incoming.navigator;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.navigator.FavouriteChangedComposer;

public class DeleteFavouriteRoomEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int roomId = this.packet.readInt();

        Room room = Emulator.getGameEnvironment().getRoomManager().getRoom(roomId);

        if (room != null) {
            if (this.client.getHabbo().getHabboStats().hasFavoriteRoom(roomId)) {
                this.client.getHabbo().getHabboStats().removeFavoriteRoom(roomId);
            }

            this.client.sendResponse(new FavouriteChangedComposer(roomId, false));
        }
    }
}