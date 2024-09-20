package com.eu.habbo.messages.outgoing.room.session;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class OpenConnectionComposer extends MessageComposer {
    private final int flatId;

    public OpenConnectionComposer(int flatId) {
        this.flatId = flatId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.OpenConnection);
        this.response.appendInt(this.flatId);
        return this.response;
    }
}
