package com.eu.habbo.messages.incoming.room.avatar;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.achievements.AchievementManager;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.engine.UserChangeComposer;
import com.eu.habbo.plugin.events.users.UserSavedMottoEvent;

public class ChangeMottoEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        String motto = this.packet.readString();
        UserSavedMottoEvent event = new UserSavedMottoEvent(this.client.getHabbo(), this.client.getHabbo().getHabboInfo().getMotto(), motto);
        Emulator.getPluginManager().fireEvent(event);
        motto = event.newMotto;
        
        if(motto.length() <= Emulator.getConfig().getInt("motto.max_length", 38)) {
            this.client.getHabbo().getHabboInfo().setMotto(motto);
            this.client.getHabbo().getHabboInfo().run();
        }

        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null) {
            this.client.getHabbo().getHabboInfo().getCurrentRoom().sendComposer(new UserChangeComposer(this.client.getHabbo()).compose());
        } else {
            this.client.sendResponse(new UserChangeComposer(this.client.getHabbo()));
        }

        AchievementManager.progressAchievement(this.client.getHabbo(), Emulator.getGameEnvironment().getAchievementManager().getAchievement("Motto"));
    }
}
