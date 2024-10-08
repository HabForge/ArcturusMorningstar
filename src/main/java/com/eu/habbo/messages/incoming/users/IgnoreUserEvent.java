package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.achievements.AchievementManager;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.IgnoreResultComposer;

public class IgnoreUserEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();

        if (room != null) {
            String username = this.packet.readString();

            Habbo habbo = room.getHabbo(username);

            if (habbo != null) {
                if (habbo == this.client.getHabbo())
                    return;

                if (this.client.getHabbo().getHabboStats().ignoreUser(this.client, habbo.getHabboInfo().getId())) {
                    this.client.sendResponse(new IgnoreResultComposer(habbo, IgnoreResultComposer.IGNORED));
                    AchievementManager.progressAchievement(this.client.getHabbo(), Emulator.getGameEnvironment().getAchievementManager().getAchievement("SelfModIgnoreSeen"));
                }
            }
        }
    }
}
