package com.eu.habbo.messages.outgoing.friendfurni;

import com.eu.habbo.habbohotel.items.interactions.InteractionLoveLock;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class FriendFurniOtherLockConfirmedComposer extends MessageComposer {
    private final InteractionLoveLock loveLock;

    public FriendFurniOtherLockConfirmedComposer(InteractionLoveLock loveLock) {
        this.loveLock = loveLock;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FriendFurniOtherLockConfirmed);
        this.response.appendInt(this.loveLock.getId());
        return this.response;
    }
}
