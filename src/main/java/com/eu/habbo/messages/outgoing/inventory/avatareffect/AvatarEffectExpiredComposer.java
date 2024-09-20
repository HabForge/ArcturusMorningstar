package com.eu.habbo.messages.outgoing.inventory.avatareffect;

import com.eu.habbo.habbohotel.users.inventory.EffectsComponent;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class AvatarEffectExpiredComposer extends MessageComposer {
    public final EffectsComponent.HabboEffect effect;

    public AvatarEffectExpiredComposer(EffectsComponent.HabboEffect effect) {
        this.effect = effect;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AvatarEffectExpired);
        this.response.appendInt(this.effect.effect);
        return this.response;
    }
}