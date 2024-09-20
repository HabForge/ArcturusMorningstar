package com.eu.habbo.messages.incoming.groupforums;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.groupforums.ForumDataComposer;

public class GetForumStatsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = packet.readInt();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);

        if (guild == null)
            return;

        this.client.sendResponse(new ForumDataComposer(guild, this.client.getHabbo()));

        if (!Emulator.getGameEnvironment().getGuildManager().hasViewedForum(this.client.getHabbo().getHabboInfo().getId(), guildId)) {
            Emulator.getGameEnvironment().getGuildManager().addView(this.client.getHabbo().getHabboInfo().getId(), guildId);
        }
    }
}