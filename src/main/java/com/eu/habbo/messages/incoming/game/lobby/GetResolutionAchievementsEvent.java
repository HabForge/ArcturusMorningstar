package com.eu.habbo.messages.incoming.game.lobby;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.events.resolution.NewYearResolutionComposer;

public class GetResolutionAchievementsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int itemId = this.packet.readInt();
        int viewAll = this.packet.readInt();

        if (viewAll == 0) {
            this.client.sendResponse(new NewYearResolutionComposer());
        }
    }
}
