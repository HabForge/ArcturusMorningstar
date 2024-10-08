package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.guilds.GuildState;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.HabboGroupDetailsComposer;
import com.eu.habbo.messages.outgoing.users.HabboGroupJoinFailedComposer;

public class JoinHabboGroupEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();

        if (this.client.getHabbo().getHabboStats().hasGuild(guildId))
            return;

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);

        if (guild == null)
            return;

        if (guild.getState() == GuildState.CLOSED || guild.getState() == GuildState.LARGE_CLOSED) {
            this.client.sendResponse(new HabboGroupJoinFailedComposer(HabboGroupJoinFailedComposer.GROUP_CLOSED));
            return;
        }

        Emulator.getGameEnvironment().getGuildManager().joinGuild(guild, this.client, 0, false);
        this.client.sendResponse(new HabboGroupDetailsComposer(guild, this.client, false, Emulator.getGameEnvironment().getGuildManager().getGuildMember(guild, this.client.getHabbo())));

        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();

        if (room == null || room.getGuildId() != guildId)
            return;

        room.refreshRightsForHabbo(this.client.getHabbo());
    }
}
