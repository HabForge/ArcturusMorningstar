package com.eu.habbo.messages.outgoing.recycler;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class RecyclerStatusComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.RecyclerStatus);
        this.response.appendInt(1);
        this.response.appendInt(0);
        return this.response;
    }
}
