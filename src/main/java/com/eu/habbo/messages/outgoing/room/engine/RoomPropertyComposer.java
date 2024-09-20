package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class RoomPropertyComposer extends MessageComposer {
    private final String type;
    private final String value;

    public RoomPropertyComposer(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.RoomProperty);
        this.response.appendString(this.type);
        this.response.appendString(this.value);
        return this.response;
    }
}
