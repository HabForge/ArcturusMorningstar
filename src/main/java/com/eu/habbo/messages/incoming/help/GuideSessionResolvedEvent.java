package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guides.GuideTour;
import com.eu.habbo.messages.incoming.MessageHandler;

public class GuideSessionResolvedEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        GuideTour tour = Emulator.getGameEnvironment().getGuideManager().getGuideTourByHabbo(this.client.getHabbo());

        if (tour != null) {
            Emulator.getGameEnvironment().getGuideManager().endSession(tour);
        }
    }
}
