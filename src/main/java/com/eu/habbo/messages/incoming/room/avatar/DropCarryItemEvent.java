package com.eu.habbo.messages.incoming.room.avatar;

import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.action.CarryObjectComposer;

public class DropCarryItemEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();
        this.client.getHabbo().getRoomUnit().setHandItem(0);
        if (room != null) {
            room.unIdle(this.client.getHabbo());
            room.sendComposer(new CarryObjectComposer(this.client.getHabbo().getRoomUnit()).compose());
        }
    }
}
