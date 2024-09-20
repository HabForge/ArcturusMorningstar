package com.eu.habbo.messages.outgoing.room.chat;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class FloodControlComposer extends MessageComposer {
    private final int time;

    public FloodControlComposer(int time) {
        this.time = time;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FloodControl);
        this.response.appendInt(this.time);
        return this.response;
    }
}
