package com.eu.habbo.messages.outgoing.users;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ExtendedProfileChangedComposer extends MessageComposer {
    private final int userId;

    public ExtendedProfileChangedComposer(int userId) {
        this.userId = userId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ExtendedProfileChanged);
        this.response.appendInt(this.userId);
        return this.response;
    }
}