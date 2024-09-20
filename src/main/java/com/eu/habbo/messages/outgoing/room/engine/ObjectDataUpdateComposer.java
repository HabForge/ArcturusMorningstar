package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ObjectDataUpdateComposer extends MessageComposer {
    private final HabboItem item;

    public ObjectDataUpdateComposer(HabboItem item) {
        this.item = item;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ObjectDataUpdate);
        this.response.appendString(this.item.getId() + "");
        this.item.serializeExtradata(this.response);
        return this.response;
    }
}