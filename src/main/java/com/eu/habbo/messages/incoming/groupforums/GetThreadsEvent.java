package com.eu.habbo.messages.incoming.groupforums;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.error.ErrorReportComposer;
import com.eu.habbo.messages.outgoing.groupforums.ForumDataComposer;
import com.eu.habbo.messages.outgoing.groupforums.ForumThreadsComposer;

public class GetThreadsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = packet.readInt();
        int index = packet.readInt();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);

        if (guild == null) {
            this.client.sendResponse(new ErrorReportComposer(404));
            return;
        }

        this.client.sendResponse(new ForumDataComposer(guild, this.client.getHabbo()));
        this.client.sendResponse(new ForumThreadsComposer(guild, index));
    }
}