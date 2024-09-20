package com.eu.habbo.messages.outgoing.room.furniture;

import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class OpenPetPackageRequestedComposer extends MessageComposer {
    private final HabboItem item;

    public OpenPetPackageRequestedComposer(HabboItem item) {
        this.item = item;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.OpenPetPackageRequested);
        this.response.appendInt(this.item.getId());
        return this.response;
    }
}