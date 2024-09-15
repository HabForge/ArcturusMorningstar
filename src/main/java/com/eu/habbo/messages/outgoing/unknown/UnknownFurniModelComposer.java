package com.eu.habbo.messages.outgoing.unknown;

import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class UnknownFurniModelComposer extends MessageComposer {
    private final HabboItem item;
    private final int unknownInt;

    public UnknownFurniModelComposer(HabboItem item, int unknownInt) {
        this.item = item;
        this.unknownInt = unknownInt;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.PostItPlaced);
        this.response.appendInt(this.item.getId());
        this.response.appendInt(this.unknownInt);
        return this.response;
    }
}