package com.eu.habbo.messages.outgoing.gifts;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class PhoneCollectionStateComposer extends MessageComposer {
    private final int unknownInt1;
    private final int unknownInt2;
    private final int unknownInt3;

    public PhoneCollectionStateComposer(int unknownInt1, int unknownInt2, int unknownInt3) {
        this.unknownInt1 = unknownInt1;
        this.unknownInt2 = unknownInt2;
        this.unknownInt3 = unknownInt3;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.PhoneCollectionState);
        this.response.appendInt(this.unknownInt1);
        this.response.appendInt(this.unknownInt2);
        this.response.appendInt(this.unknownInt3);
        return this.response;
    }
}