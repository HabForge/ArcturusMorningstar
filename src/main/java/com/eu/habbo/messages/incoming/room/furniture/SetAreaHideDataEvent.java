package com.eu.habbo.messages.incoming.room.furniture;

import com.eu.habbo.habbohotel.items.interactions.InteractionAreaHider;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.incoming.MessageHandler;

public class SetAreaHideDataEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();
        if (room == null)
            return;

        if (!room.hasRights(this.client.getHabbo()))
            return;

        HabboItem item = room.getHabboItem(this.packet.readInt());
        if (item == null)
            return;

        if (item instanceof InteractionAreaHider) {
            ((InteractionAreaHider) item).saveData(this.packet);
        }
    }
}
