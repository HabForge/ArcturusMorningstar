package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

import java.util.Set;

public class ObjectsDataUpdateComposer extends MessageComposer {
    private final Set<HabboItem> items;

    public ObjectsDataUpdateComposer(Set<HabboItem> items) {
        this.items = items;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ObjectsDataUpdate);
        this.response.appendInt(this.items.size());

        for (HabboItem item : this.items) {
            this.response.appendInt(item.getId());
            item.serializeExtradata(this.response);
        }

        return this.response;
    }
}