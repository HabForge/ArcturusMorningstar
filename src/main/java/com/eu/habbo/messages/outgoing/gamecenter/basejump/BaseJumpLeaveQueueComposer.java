package com.eu.habbo.messages.outgoing.gamecenter.basejump;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class BaseJumpLeaveQueueComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.BaseJumpLeaveQueue);
        this.response.appendInt(3);
        return this.response;
    }
}