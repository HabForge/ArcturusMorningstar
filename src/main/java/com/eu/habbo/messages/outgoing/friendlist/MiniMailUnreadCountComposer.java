package com.eu.habbo.messages.outgoing.friendlist;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class MiniMailUnreadCountComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.MiniMailUnreadCount);
        this.response.appendInt(0);
        return this.response;
    }
}
