package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.forums.ForumThread;
import com.eu.habbo.habbohotel.modtool.CfhTopic;
import com.eu.habbo.habbohotel.modtool.ModToolIssue;
import com.eu.habbo.habbohotel.modtool.ModToolTicketType;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.help.CallForHelpResultComposer;
import com.eu.habbo.threading.runnables.InsertModToolIssue;

public class CallForHelpFromForumMessageEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int groupId = this.packet.readInt();
        int threadId = this.packet.readInt();
        int commentId = this.packet.readInt();
        int topicId = this.packet.readInt();
        String message = this.packet.readString();

        CfhTopic topic = Emulator.getGameEnvironment().getModToolManager().getCfhTopic(topicId);

        if (topic == null) return;

        ForumThread thread = ForumThread.getById(threadId);

        if (thread == null) return;

        Habbo opener = Emulator.getGameEnvironment().getHabboManager().getHabbo(thread.getOpenerId());

        ModToolIssue issue = new ModToolIssue(this.client.getHabbo().getHabboInfo().getId(), this.client.getHabbo().getHabboInfo().getUsername(), thread.getOpenerId(), opener == null ? "" : opener.getHabboInfo().getUsername(), 0, message, ModToolTicketType.DISCUSSION);
        issue.category = topicId;
        issue.groupId = groupId;
        issue.threadId = threadId;
        issue.commentId = commentId;
        new InsertModToolIssue(issue).run();

        this.client.sendResponse(new CallForHelpResultComposer(CallForHelpResultComposer.REPORT_RECEIVED, message));
        Emulator.getGameEnvironment().getModToolManager().addTicket(issue);
        Emulator.getGameEnvironment().getModToolManager().updateTicketToMods(issue);
    }
}
