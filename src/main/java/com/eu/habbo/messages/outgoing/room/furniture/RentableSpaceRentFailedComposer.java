package com.eu.habbo.messages.outgoing.room.furniture;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class RentableSpaceRentFailedComposer extends MessageComposer {
    private final int itemId;

    public RentableSpaceRentFailedComposer(int itemId) {
        this.itemId = itemId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.RentableSpaceRentFailed);
        this.response.appendInt(this.itemId);
        return this.response;
    }
}
