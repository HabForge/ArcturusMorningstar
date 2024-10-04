package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import gnu.trove.set.hash.THashSet;

public class ObjectRemoveMultipleComposer extends MessageComposer {

    private final THashSet<? extends HabboItem> items;

    public ObjectRemoveMultipleComposer(THashSet<? extends HabboItem> items) {
        this.items = items;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ObjectRemoveMultiple);
        this.response.appendInt(this.items.size());
        for (HabboItem item : this.items) {
            this.response.appendInt(item.getId());
        }
        this.response.appendInt(-1); // pickerId (-1 by default for AreaHiders)
        return this.response;
    }
}