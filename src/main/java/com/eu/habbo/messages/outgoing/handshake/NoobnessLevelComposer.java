package com.eu.habbo.messages.outgoing.handshake;

import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class NoobnessLevelComposer extends MessageComposer {
    private final Habbo habbo;

    public NoobnessLevelComposer(Habbo habbo) {
        this.habbo = habbo;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.NoobnessLevel);
        this.response.appendInt(this.habbo.noobStatus());
        return this.response;
    }
}
