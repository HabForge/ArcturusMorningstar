package com.eu.habbo.messages.incoming.inventory.pets;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.inventory.InventoryPetsComposer;

public class GetPetInventoryEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new InventoryPetsComposer(this.client.getHabbo()));
    }
}
