package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.GuildCreationInfoComposer;
import gnu.trove.set.hash.THashSet;

import java.util.List;

public class GetGuildCreationInfoEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        List<Room> rooms = Emulator.getGameEnvironment().getRoomManager().getRoomsForHabbo(this.client.getHabbo());

        THashSet<Room> roomList = new THashSet<Room>();

        for (Room room : rooms) {
            if (room.getGuildId() == 0)
                roomList.add(room);
        }

        this.client.sendResponse(new GuildCreationInfoComposer(roomList));
    }
}
