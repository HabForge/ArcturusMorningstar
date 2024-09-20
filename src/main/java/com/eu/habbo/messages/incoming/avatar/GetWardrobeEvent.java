package com.eu.habbo.messages.incoming.avatar;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.avatar.WardrobeComposer;

public class GetWardrobeEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new WardrobeComposer(this.client.getHabbo().getInventory().getWardrobeComponent()));
    }
}
