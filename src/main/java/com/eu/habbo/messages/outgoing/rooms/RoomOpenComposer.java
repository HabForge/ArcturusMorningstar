package com.eu.habbo.messages.outgoing.rooms;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class RoomOpenComposer extends MessageComposer {
    private final int flatId;

    public RoomOpenComposer(int flatId) {
        this.flatId = flatId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.OpenConnection);
        this.response.appendInt(this.flatId);
        return this.response;
    }
}
