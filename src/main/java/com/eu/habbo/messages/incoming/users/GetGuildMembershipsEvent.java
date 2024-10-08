package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.GuildMembershipsComposer;
import gnu.trove.set.hash.THashSet;

public class GetGuildMembershipsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        THashSet<Guild> guilds = new THashSet<Guild>();

        for (int i : this.client.getHabbo().getHabboStats().guilds) {
            if (i == 0)
                continue;

            Guild g = Emulator.getGameEnvironment().getGuildManager().getGuild(i);

            if (g != null) {
                guilds.add(g);
            }
        }

        this.client.sendResponse(new GuildMembershipsComposer(guilds, this.client.getHabbo()));
    }
}
