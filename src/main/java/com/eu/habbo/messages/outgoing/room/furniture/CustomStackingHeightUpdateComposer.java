package com.eu.habbo.messages.outgoing.room.furniture;

import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class CustomStackingHeightUpdateComposer extends MessageComposer {
    private final HabboItem item;
    private final int height;

    public CustomStackingHeightUpdateComposer(HabboItem item, int height) {
        this.item = item;
        this.height = height;

    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CustomStackingHeightUpdate);
        this.response.appendInt(this.item.getId());
        this.response.appendInt(this.height);
        return this.response;
    }
}
