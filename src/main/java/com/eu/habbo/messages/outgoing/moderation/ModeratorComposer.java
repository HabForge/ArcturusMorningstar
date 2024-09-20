package com.eu.habbo.messages.outgoing.moderation;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ModeratorComposer extends MessageComposer {
    private final String message;
    private final String link;

    public ModeratorComposer(String message, String link) {
        this.message = message;
        this.link = link;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.Moderator);
        this.response.appendString(this.message);
        this.response.appendString(this.link);
        return this.response;
    }
}
