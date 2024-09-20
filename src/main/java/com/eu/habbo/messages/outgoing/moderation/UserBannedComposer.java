package com.eu.habbo.messages.outgoing.moderation;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class UserBannedComposer extends MessageComposer {
    private final String message;

    public UserBannedComposer(String message) {
        this.message = message;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.UserBanned);
        this.response.appendString(this.message);
        return this.response;
    }
}
