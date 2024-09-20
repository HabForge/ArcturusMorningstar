package com.eu.habbo.messages.outgoing.inventory.avatareffect;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class AvatarEffectSelectedComposer extends MessageComposer {
    private final int type;

    public AvatarEffectSelectedComposer(int type) {
        this.type = type;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AvatarEffectSelected);
        this.response.appendInt(this.type);
        return this.response;
    }
}