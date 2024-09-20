package com.eu.habbo.messages.outgoing.room.session;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class RoomForwardComposer extends MessageComposer {
    private final int roomId;

    public RoomForwardComposer(int roomId) {
        this.roomId = roomId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.RoomForward);
        this.response.appendInt(this.roomId);
        return this.response;
    }
}
