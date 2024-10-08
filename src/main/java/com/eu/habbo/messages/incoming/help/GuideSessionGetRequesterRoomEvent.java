package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guides.GuideTour;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.help.GuideSessionRequesterRoomComposer;

public class GuideSessionGetRequesterRoomEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        GuideTour tour = Emulator.getGameEnvironment().getGuideManager().getGuideTourByHelper(this.client.getHabbo());

        if (tour != null) {
            this.client.sendResponse(new GuideSessionRequesterRoomComposer(tour.getNoob().getHabboInfo().getCurrentRoom()));
        }
    }
}
