package com.eu.habbo.messages.incoming.inventory.badges;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.inventory.InventoryBadgesComposer;

public class GetBadgesEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new InventoryBadgesComposer(this.client.getHabbo()));
    }
}
