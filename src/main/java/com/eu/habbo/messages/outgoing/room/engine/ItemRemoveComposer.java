package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ItemRemoveComposer extends MessageComposer {
    private final HabboItem item;
    private final boolean hideAnimation;

    public ItemRemoveComposer(HabboItem item, boolean hideAnimation) {
        this.item = item;
        this.hideAnimation = hideAnimation;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ItemRemove);
        this.response.appendString(this.item.getId() + "");
        this.response.appendInt(this.hideAnimation ? -1 : this.item.getUserId());
        return this.response;
    }
}
