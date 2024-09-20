package com.eu.habbo.messages.outgoing.tracking;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class LatencyPingResponseComposer extends MessageComposer {
    private final int id;

    public LatencyPingResponseComposer(int id) {
        this.id = id;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.LatencyPingResponse);
        this.response.appendInt(this.id);
        return this.response;
    }
}
