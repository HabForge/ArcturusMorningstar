package com.eu.habbo.messages.outgoing.mysterybox;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GotMysteryBoxPrizeComposer extends MessageComposer {
    private final String type;
    private final int itemId;

    public GotMysteryBoxPrizeComposer(String type, int itemId) {
        this.type = type;
        this.itemId = itemId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GotMysteryBoxPrize);
        this.response.appendString(this.type);
        this.response.appendInt(this.itemId);
        return this.response;
    }
}
