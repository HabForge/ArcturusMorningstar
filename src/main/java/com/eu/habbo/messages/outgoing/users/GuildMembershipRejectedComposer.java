package com.eu.habbo.messages.outgoing.users;

import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GuildMembershipRejectedComposer extends MessageComposer {
    private final Guild guild;

    public GuildMembershipRejectedComposer(Guild guild) {
        this.guild = guild;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GuildMembershipRejected);
        this.response.appendInt(this.guild.getId());
        this.response.appendInt(0);
        return this.response;
    }
}
