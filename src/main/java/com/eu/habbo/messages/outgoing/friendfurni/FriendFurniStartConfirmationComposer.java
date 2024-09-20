package com.eu.habbo.messages.outgoing.friendfurni;

import com.eu.habbo.habbohotel.items.interactions.InteractionLoveLock;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class FriendFurniStartConfirmationComposer extends MessageComposer {
    private final InteractionLoveLock loveLock;

    public FriendFurniStartConfirmationComposer(InteractionLoveLock loveLock) {
        this.loveLock = loveLock;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FriendFurniStartConfirmation);
        this.response.appendInt(this.loveLock.getId());
        this.response.appendBoolean(true);
        return this.response;
    }
}
