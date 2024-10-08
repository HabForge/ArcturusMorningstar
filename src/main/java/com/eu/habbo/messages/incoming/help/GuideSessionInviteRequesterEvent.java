package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guides.GuideTour;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.help.GuideSessionInvitedToGuideRoomComposer;

public class GuideSessionInviteRequesterEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        GuideTour tour = Emulator.getGameEnvironment().getGuideManager().getGuideTourByHelper(this.client.getHabbo());

        if (tour != null) {
            ServerMessage message = new GuideSessionInvitedToGuideRoomComposer(this.client.getHabbo().getHabboInfo().getCurrentRoom()).compose();
            tour.getNoob().getClient().sendResponse(message);
            tour.getHelper().getClient().sendResponse(message);
        }
    }
}
