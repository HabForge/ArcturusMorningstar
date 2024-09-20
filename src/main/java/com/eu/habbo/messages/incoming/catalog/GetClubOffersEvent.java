package com.eu.habbo.messages.incoming.catalog;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.catalog.HabboClubOffersComposer;

public class GetClubOffersEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new HabboClubOffersComposer(this.client.getHabbo(), this.packet.readInt()));
    }
}
