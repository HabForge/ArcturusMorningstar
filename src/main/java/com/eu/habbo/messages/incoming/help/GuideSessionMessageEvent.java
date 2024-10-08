package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guides.GuideChatMessage;
import com.eu.habbo.habbohotel.guides.GuideTour;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.help.GuideSessionMessageComposer;

public class GuideSessionMessageEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        GuideTour tour = Emulator.getGameEnvironment().getGuideManager().getGuideTourByHabbo(this.client.getHabbo());

        if (tour != null) {
            GuideChatMessage chatMessage = new GuideChatMessage(this.client.getHabbo().getHabboInfo().getId(), this.packet.readString(), Emulator.getIntUnixTimestamp());
            tour.addMessage(chatMessage);
            ServerMessage serverMessage = new GuideSessionMessageComposer(chatMessage).compose();
            tour.getHelper().getClient().sendResponse(serverMessage);
            tour.getNoob().getClient().sendResponse(serverMessage);
        }
    }
}
