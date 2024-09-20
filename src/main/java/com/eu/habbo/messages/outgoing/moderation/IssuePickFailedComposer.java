package com.eu.habbo.messages.outgoing.moderation;

import com.eu.habbo.habbohotel.modtool.ModToolIssue;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class IssuePickFailedComposer extends MessageComposer {
    private final ModToolIssue issue;

    public IssuePickFailedComposer(ModToolIssue issue) {
        this.issue = issue;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.IssuePickFailed);
        this.issue.serialize(this.response);
        return this.response;
    }
}
