package com.eu.habbo.messages.incoming.inventory.badges;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.inventory.badges.BadgesComposer;

public class GetBadgesEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new BadgesComposer(this.client.getHabbo()));
    }
}
