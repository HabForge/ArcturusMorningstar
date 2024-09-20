package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.GuildEditInfoComposer;

public class GetGuildEditInfoEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(this.packet.readInt());

        this.client.sendResponse(new GuildEditInfoComposer(guild));
    }
}
