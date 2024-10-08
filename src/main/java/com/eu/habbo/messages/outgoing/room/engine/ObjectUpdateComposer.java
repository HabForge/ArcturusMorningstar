package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.items.interactions.InteractionGift;
import com.eu.habbo.habbohotel.items.interactions.InteractionMusicDisc;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ObjectUpdateComposer extends MessageComposer {
    private final HabboItem item;

    public ObjectUpdateComposer(HabboItem item) {
        this.item = item;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ObjectUpdate);
        this.item.serializeFloorData(this.response);
        this.response.appendInt(this.item instanceof InteractionGift ? ((((InteractionGift) this.item).getColorId() * 1000) + ((InteractionGift) this.item).getRibbonId()) : (this.item instanceof InteractionMusicDisc ? ((InteractionMusicDisc) this.item).getSongId() : item.isUsable() ? 0 : 0));
        this.item.serializeExtradata(this.response);
        this.response.appendInt(-1);
        this.response.appendInt(0);
        this.response.appendInt(this.item.getUserId());
        return this.response;
    }
}
