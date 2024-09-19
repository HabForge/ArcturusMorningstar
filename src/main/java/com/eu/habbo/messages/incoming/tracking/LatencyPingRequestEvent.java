package com.eu.habbo.messages.incoming.tracking;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.handshake.PongComposer;

public class LatencyPingRequestEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new PongComposer(this.packet.readInt()));
    }
}
