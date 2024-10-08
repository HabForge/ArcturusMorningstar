package com.eu.habbo.messages.incoming.room.furniture;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.incoming.MessageHandler;

public class SetMannequinNameEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();
        if (room == null || !room.isOwner(this.client.getHabbo()))
            return;

        HabboItem item = room.getHabboItem(this.packet.readInt());
        if (item == null)
            return;

        String[] data = item.getExtradata().split(":");
        String name = this.packet.readString();

        if (name.length() < 3 || name.length() > 15) {
            name = Emulator.getTexts().getValue("hotel.mannequin.name.default", "My look");
        }

        if (data.length == 3) {
            item.setExtradata(this.client.getHabbo().getHabboInfo().getGender().name().toUpperCase() + ":" + data[1] + ":" + name);
        } else {
            item.setExtradata(this.client.getHabbo().getHabboInfo().getGender().name().toUpperCase() + ":" + this.client.getHabbo().getHabboInfo().getLook() + ":" + name);
        }
        item.needsUpdate(true);
        Emulator.getThreading().run(item);
        room.updateItem(item);
    }
}
