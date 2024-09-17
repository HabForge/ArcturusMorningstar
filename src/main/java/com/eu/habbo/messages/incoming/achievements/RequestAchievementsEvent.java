package com.eu.habbo.messages.incoming.achievements;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.achievements.AchievementListComposer;

public class RequestAchievementsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new AchievementListComposer(0, this.client.getHabbo()));
    }
}
