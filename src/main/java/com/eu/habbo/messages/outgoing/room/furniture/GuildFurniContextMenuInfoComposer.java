package com.eu.habbo.messages.outgoing.room.furniture;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GuildFurniContextMenuInfoComposer extends MessageComposer {
    private final HabboItem item;
    private final Guild guild;
    private final Habbo habbo;

    public GuildFurniContextMenuInfoComposer(Habbo habbo, Guild guild, HabboItem item) {
        this.habbo = habbo;
        this.item = item;
        this.guild = guild;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GuildFurniContextMenuInfo);
        this.response.appendInt(item.getId());
        this.response.appendInt(this.guild.getId());
        this.response.appendString(this.guild.getName());
        this.response.appendInt(this.guild.getRoomId());
        this.response.appendBoolean(Emulator.getGameEnvironment().getGuildManager().getGuildMember(this.guild, this.habbo) != null); //User Joined.
        this.response.appendBoolean(this.guild.hasForum()); //Has Forum.
        return this.response;
    }
}
