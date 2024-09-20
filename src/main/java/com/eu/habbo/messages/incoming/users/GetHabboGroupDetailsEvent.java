package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.HabboGroupDetailsComposer;

public class GetHabboGroupDetailsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();
        boolean newWindow = this.packet.readBoolean();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);

        if (guild != null) {
            this.client.sendResponse(new HabboGroupDetailsComposer(guild, this.client, newWindow, Emulator.getGameEnvironment().getGuildManager().getGuildMember(guild, this.client.getHabbo())));
        }
    }
}
