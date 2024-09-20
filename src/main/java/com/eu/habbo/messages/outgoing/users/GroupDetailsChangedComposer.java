package com.eu.habbo.messages.outgoing.users;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GroupDetailsChangedComposer extends MessageComposer {
    private final int guildId;

    public GroupDetailsChangedComposer(int guildId) {
        this.guildId = guildId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GroupDetailsChanged);
        this.response.appendInt(this.guildId);
        return this.response;
    }
}