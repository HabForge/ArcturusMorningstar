package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ObjectRemoveComposer extends MessageComposer {
    private final HabboItem item;
    private final boolean noUser;

    public ObjectRemoveComposer(HabboItem item) {
        this.item = item;
        this.noUser = false;
    }

    public ObjectRemoveComposer(HabboItem item, boolean noUser) {
        this.item = item;
        this.noUser = noUser;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ObjectRemove);

        this.response.appendString(this.item.getId() + "");
        this.response.appendBoolean(false);
        this.response.appendInt(this.noUser ? 0 : this.item.getUserId());
        this.response.appendInt(0);

        return this.response;
    }
}
