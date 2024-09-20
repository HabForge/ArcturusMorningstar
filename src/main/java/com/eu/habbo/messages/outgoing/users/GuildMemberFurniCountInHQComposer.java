package com.eu.habbo.messages.outgoing.users;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GuildMemberFurniCountInHQComposer extends MessageComposer {
    private int userId;
    private int furniCount;

    public GuildMemberFurniCountInHQComposer(int userId, int furniCount) {
        this.userId = userId;
        this.furniCount = furniCount;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GuildMemberFurniCountInHQ);
        this.response.appendInt(this.userId);
        this.response.appendInt(this.furniCount);
        return this.response;
    }
}
