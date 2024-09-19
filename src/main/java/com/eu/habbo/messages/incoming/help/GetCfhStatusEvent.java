package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.modtool.ModToolSanctionInfoComposer;

public class GetCfhStatusEvent extends MessageHandler {

    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new ModToolSanctionInfoComposer(this.client.getHabbo()));
    }
}
