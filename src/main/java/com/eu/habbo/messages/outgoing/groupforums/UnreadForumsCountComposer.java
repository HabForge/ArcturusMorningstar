package com.eu.habbo.messages.outgoing.groupforums;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class UnreadForumsCountComposer extends MessageComposer {
    public final int count;

    public UnreadForumsCountComposer(int count) {
        this.count = count;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.UnreadForumsCount);
        this.response.appendInt(this.count);
        return this.response;
    }
}