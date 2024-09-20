package com.eu.habbo.messages.incoming.recycler;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.recycler.RecyclerStatusComposer;

public class GetRecyclerStatusEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new RecyclerStatusComposer());
    }
}
