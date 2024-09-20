package com.eu.habbo.messages.incoming.tracking;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.tracking.LatencyPingResponseComposer;

public class LatencyPingRequestEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new LatencyPingResponseComposer(this.packet.readInt()));
    }
}
