package com.eu.habbo.messages.outgoing.room.bots;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class BotForceOpenContextMenuComposer extends MessageComposer {
    private final int botId;

    public BotForceOpenContextMenuComposer(int botId) {
        this.botId = botId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.BotForceOpenContextMenu);
        this.response.appendInt(this.botId);
        return this.response;
    }
}