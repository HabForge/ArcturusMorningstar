package com.eu.habbo.messages.outgoing.users;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ChangeEmailResultComposer extends MessageComposer {
    private final int result;

    public ChangeEmailResultComposer(int result) {
        this.result = result;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ChangeEmailResult);
        this.response.appendInt(this.result);
        return this.response;
    }
}