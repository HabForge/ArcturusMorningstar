package com.eu.habbo.messages.incoming.inventory.bots;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.inventory.bots.BotInventoryComposer;

public class GetBotInventoryEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new BotInventoryComposer(this.client.getHabbo()));
    }
}
