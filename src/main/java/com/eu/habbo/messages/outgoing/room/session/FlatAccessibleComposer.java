package com.eu.habbo.messages.outgoing.room.session;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import com.eu.habbo.protocol.Revision;

public class FlatAccessibleComposer extends MessageComposer {
    private final Revision revision;
    private final int flatId;
    private final String username;

    public FlatAccessibleComposer(Revision revision, int flatId, String username) {
        this.revision = revision;
        this.flatId = flatId;
        this.username = username;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FlatAccessible);

        if (this.revision == Revision.PRODUCTION_201611291003_338511768) {
            this.response.appendString(this.username);
        } else {
            this.response.appendInt(this.flatId);
            this.response.appendString(this.username);
        }

        return this.response;
    }
}