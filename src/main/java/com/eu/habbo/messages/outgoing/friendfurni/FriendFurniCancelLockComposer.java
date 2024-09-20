package com.eu.habbo.messages.outgoing.friendfurni;

import com.eu.habbo.habbohotel.items.interactions.InteractionLoveLock;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class FriendFurniCancelLockComposer extends MessageComposer {
    private final InteractionLoveLock loveLock;

    public FriendFurniCancelLockComposer(InteractionLoveLock loveLock) {
        this.loveLock = loveLock;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FriendFurniCancelLock);
        this.response.appendInt(this.loveLock.getId());
        return this.response;
    }
}
