package com.eu.habbo.messages.incoming.inventory.achievements;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.inventory.achievements.AchievementsComposer;

public class GetAchievementsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new AchievementsComposer(0, this.client.getHabbo()));
    }
}
