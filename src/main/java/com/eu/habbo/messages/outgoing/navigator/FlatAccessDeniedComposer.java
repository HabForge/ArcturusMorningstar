package com.eu.habbo.messages.outgoing.navigator;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class FlatAccessDeniedComposer extends MessageComposer {
    private final String habbo;

    public FlatAccessDeniedComposer(String habbo) {
        this.habbo = habbo;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FlatAccessDenied);
        this.response.appendString(this.habbo);
        return this.response;
    }
}
