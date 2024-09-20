package com.eu.habbo.messages.outgoing.quest;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class QuestCancelledComposer extends MessageComposer {
    private final boolean expired;

    public QuestCancelledComposer(boolean expired) {
        this.expired = expired;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.QuestCancelled);
        this.response.appendBoolean(this.expired);
        return this.response;
    }
}