package com.eu.habbo.messages.incoming.roomsettings;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.achievements.AchievementManager;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.chat.RoomFilterSettingsComposer;

public class GetCustomRoomFilterEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Room room = Emulator.getGameEnvironment().getRoomManager().getRoom(this.packet.readInt());

        if (room != null && room.hasRights(this.client.getHabbo())) {
            this.client.sendResponse(new RoomFilterSettingsComposer(room));

            AchievementManager.progressAchievement(this.client.getHabbo(), Emulator.getGameEnvironment().getAchievementManager().getAchievement("SelfModRoomFilterSeen"));
        }
    }
}
