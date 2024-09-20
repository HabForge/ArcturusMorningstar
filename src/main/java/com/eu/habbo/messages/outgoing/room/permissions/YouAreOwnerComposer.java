package com.eu.habbo.messages.outgoing.room.permissions;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class YouAreOwnerComposer extends MessageComposer {
    private final int flatId;

    public YouAreOwnerComposer(int flatId) {
        this.flatId = flatId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.YouAreOwner);
        this.response.appendInt(this.flatId);
        return this.response;
    }
}
