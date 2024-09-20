package com.eu.habbo.messages.outgoing.catalog;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class LimitedOfferAppearingNextComposer extends MessageComposer {
    private final int time;
    private final int pageId;
    private final int itemId;
    private final String itemName;

    public LimitedOfferAppearingNextComposer(int time, int pageId, int itemId, String itemName) {
        this.time = time;
        this.pageId = pageId;
        this.itemId = itemId;
        this.itemName = itemName;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.LimitedOfferAppearingNext);
        this.response.appendInt(this.time);
        this.response.appendInt(this.pageId);
        this.response.appendInt(this.itemId);
        this.response.appendString(this.itemName);
        return this.response;
    }
}