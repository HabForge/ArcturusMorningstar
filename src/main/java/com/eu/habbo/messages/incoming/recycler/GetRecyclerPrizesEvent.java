package com.eu.habbo.messages.incoming.recycler;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.catalog.RecyclerLogicComposer;

public class GetRecyclerPrizesEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new RecyclerLogicComposer());
    }
}
