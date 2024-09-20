package com.eu.habbo.messages.incoming.landingview;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.competition.CurrentTimingCodeComposer;
import com.eu.habbo.messages.outgoing.landingview.PromoArticlesComposer;
import com.eu.habbo.messages.outgoing.quest.CommunityGoalHallOfFameComposer;

public class GetPromoArticlesEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new CurrentTimingCodeComposer("2013-05-08 13:0", "gamesmaker"));
        this.client.sendResponse(new CommunityGoalHallOfFameComposer(Emulator.getGameEnvironment().getHotelViewManager().getHallOfFame()));
        this.client.sendResponse(new PromoArticlesComposer());
    }
}
