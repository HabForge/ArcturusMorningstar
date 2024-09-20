package com.eu.habbo.messages.incoming.groupforums;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.guilds.GuildMember;
import com.eu.habbo.habbohotel.guilds.GuildRank;
import com.eu.habbo.habbohotel.guilds.forums.ForumThread;
import com.eu.habbo.habbohotel.guilds.forums.ForumThreadState;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.error.ErrorReportComposer;
import com.eu.habbo.habbohotel.notifications.BubbleAlertKeys;
import com.eu.habbo.messages.outgoing.groupforums.ForumThreadsComposer;
import com.eu.habbo.messages.outgoing.groupforums.PostThreadComposer;
import com.eu.habbo.messages.outgoing.notifications.NotificationDialogComposer;


public class ModerateThreadEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = packet.readInt();
        int threadId = packet.readInt();
        int state = packet.readInt();
        // STATE 20 - HIDDEN_BY_GUILD_ADMIN = HIDDEN BY GUILD ADMINS/ HOTEL MODERATORS
        // STATE 1 = VISIBLE THREAD

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);
        ForumThread thread = ForumThread.getById(threadId);

        if (guild == null || thread == null) {
            this.client.sendResponse(new ErrorReportComposer(404));
            return;
        }

        GuildMember member = Emulator.getGameEnvironment().getGuildManager().getGuildMember(guildId, this.client.getHabbo().getHabboInfo().getId());
        boolean hasStaffPerms = this.client.getHabbo().hasPermission(Permission.ACC_MODTOOL_TICKET_Q); // check for if they have staff perm
        boolean isGuildAdmin = (guild.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || member.getRank().equals(GuildRank.ADMIN));


        if (member == null) {
            this.client.sendResponse(new ErrorReportComposer(401));
            return;
        }
        if (!isGuildAdmin && !hasStaffPerms) {
            this.client.sendResponse(new ErrorReportComposer(403));
            return;
        }

        thread.setState(ForumThreadState.fromValue(state)); // sets state as defined in the packet
        thread.run();

        switch (state) {
            case 10:
            case 20:
                this.client.sendResponse(new NotificationDialogComposer(BubbleAlertKeys.FORUMS_THREAD_HIDDEN.key).compose());
                break;
            case 1:
                this.client.sendResponse(new NotificationDialogComposer(BubbleAlertKeys.FORUMS_THREAD_RESTORED.key).compose());
                break;
        }

        this.client.sendResponse(new PostThreadComposer(thread));
        this.client.sendResponse(new ForumThreadsComposer(guild, 0));
    }
}