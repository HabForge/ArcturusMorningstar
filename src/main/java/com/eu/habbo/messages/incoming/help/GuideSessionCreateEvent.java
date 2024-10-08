package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guides.GuideTour;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.help.GuideSessionErrorComposer;

public class GuideSessionCreateEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int type = this.packet.readInt();
        String message = this.packet.readString();

        GuideTour activeTour = Emulator.getGameEnvironment().getGuideManager().getGuideTourByHabbo(this.client.getHabbo());

        if (activeTour != null) {
            this.client.sendResponse(new GuideSessionErrorComposer(GuideSessionErrorComposer.SOMETHING_WRONG_REQUEST));
            return;
        }

        GuideTour tour = new GuideTour(this.client.getHabbo(), message);
        tour.setStartTime(Emulator.getIntUnixTimestamp());

        Emulator.getGameEnvironment().getGuideManager().findHelper(tour);
    }
}
