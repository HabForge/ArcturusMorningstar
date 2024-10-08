package com.eu.habbo.messages.incoming.groupforums;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.guilds.GuildMember;
import com.eu.habbo.habbohotel.guilds.GuildRank;
import com.eu.habbo.habbohotel.guilds.SettingsState;
import com.eu.habbo.habbohotel.guilds.forums.ForumThread;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.error.ErrorReportComposer;
import com.eu.habbo.habbohotel.notifications.BubbleAlertKeys;
import com.eu.habbo.messages.outgoing.groupforums.ForumThreadsComposer;
import com.eu.habbo.messages.outgoing.groupforums.UpdateThreadComposer;
import com.eu.habbo.messages.outgoing.notifications.NotificationDialogComposer;

public class UpdateThreadEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();
        int threadId = this.packet.readInt();
        boolean isPinned = this.packet.readBoolean();
        boolean isLocked = this.packet.readBoolean();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);
        ForumThread thread = ForumThread.getById(threadId);

        if (guild == null || thread == null) {
            this.client.sendResponse(new ErrorReportComposer(404));
            return;
        }

        boolean isStaff = this.client.getHabbo().hasPermission(Permission.ACC_MODTOOL_TICKET_Q);

        GuildMember member = Emulator.getGameEnvironment().getGuildManager().getGuildMember(guildId, this.client.getHabbo().getHabboInfo().getId());
        if (member == null) {
            this.client.sendResponse(new ErrorReportComposer(401));
            return;
        }

        boolean isAdmin = (guild.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || member.getRank().type < GuildRank.MEMBER.type);

        if ((guild.canModForum() == SettingsState.OWNER && guild.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() && !isStaff) || (guild.canModForum() == SettingsState.ADMINS && !isAdmin && !isStaff)) {
            this.client.sendResponse(new ErrorReportComposer(403));
            return;
        }

        boolean pinChanged = isPinned != thread.isPinned();
        if (pinChanged) {
            this.client.sendResponse(new NotificationDialogComposer(isPinned ? BubbleAlertKeys.FORUMS_THREAD_PINNED.key : BubbleAlertKeys.FORUMS_THREAD_UNPINNED.key).compose());
        }

        if (isLocked != thread.isLocked()) {
            this.client.sendResponse(new NotificationDialogComposer(isLocked ? BubbleAlertKeys.FORUMS_THREAD_LOCKED.key : BubbleAlertKeys.FORUMS_THREAD_UNLOCKED.key).compose());
        }

        thread.setPinned(isPinned);
        thread.setLocked(isLocked);

        thread.run();


        this.client.sendResponse(new UpdateThreadComposer(guild, thread, this.client.getHabbo(), isPinned, isLocked));

        if (pinChanged) {
            this.client.sendResponse(new ForumThreadsComposer(guild, 0));
        }
    }
}