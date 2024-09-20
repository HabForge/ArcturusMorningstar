package com.eu.habbo.messages.outgoing.users;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class HabboGroupDeactivatedComposer extends MessageComposer {
    private int guildId;

    public HabboGroupDeactivatedComposer(int guildId) {
        this.guildId = guildId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.HabboGroupDeactivated);
        this.response.appendInt(this.guildId);
        return this.response;
    }
}
