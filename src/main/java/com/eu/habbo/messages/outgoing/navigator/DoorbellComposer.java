package com.eu.habbo.messages.outgoing.navigator;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class DoorbellComposer extends MessageComposer {
    private final String habbo;

    public DoorbellComposer(String habbo) {
        this.habbo = habbo;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.Doorbell);
        this.response.appendString(this.habbo);
        return this.response;
    }
}
