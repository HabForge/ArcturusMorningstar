package com.eu.habbo.core;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.rooms.RoomChatMessage;
import com.eu.habbo.habbohotel.rooms.RoomChatMessageBubbles;
import com.eu.habbo.messages.outgoing.room.chat.WhisperComposer;
import com.eu.habbo.messages.outgoing.room.engine.UserRemoveComposer;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.events.users.UserSavedMottoEvent;

public class Easter {
    @EventHandler
    public static void onUserChangeMotto(UserSavedMottoEvent event) {
        if (Emulator.getConfig().getBoolean("easter_eggs.enabled") && event.newMotto.equalsIgnoreCase("crickey!")) {
            event.habbo.getClient().sendResponse(new WhisperComposer(new RoomChatMessage(event.newMotto, event.habbo, event.habbo, RoomChatMessageBubbles.ALERT)));

            Room room = event.habbo.getHabboInfo().getCurrentRoom();

            room.sendComposer(new UserRemoveComposer(event.habbo.getRoomUnit()).compose());
            room.sendComposer(new RoomUserPetComposer(2, 1, "FFFFFF", event.habbo).compose());

        }
    }
}
