package com.eu.habbo.messages.outgoing.room.furniture;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class OneWayDoorStatusComposer extends MessageComposer {
    private final int id;
    private final int status;

    public OneWayDoorStatusComposer(int id, int status) {
        this.id = id;
        this.status = status;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.OneWayDoorStatus);
        this.response.appendInt(this.id);
        this.response.appendInt(this.status);

        return this.response;
    }
}
