package com.eu.habbo.messages.outgoing.inventory.avatareffect;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.inventory.EffectsComponent;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

import java.util.Collection;


public class AvatarEffectsComposer extends MessageComposer {
    public final Habbo habbo;
    public final Collection<EffectsComponent.HabboEffect> effects;

    public AvatarEffectsComposer(Habbo habbo, Collection<EffectsComponent.HabboEffect> effects) {
        this.habbo = habbo;
        this.effects = effects;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AvatarEffects);


        if (this.habbo == null || this.habbo.getInventory() == null || this.habbo.getInventory().getEffectsComponent() == null || this.habbo.getInventory().getEffectsComponent().effects == null) {
            this.response.appendInt(0);
        } else {
            synchronized (this.habbo.getInventory().getEffectsComponent().effects) {
                this.response.appendInt(this.effects.size());

                for (EffectsComponent.HabboEffect effect : effects) {
                    AvatarEffectsComposer.this.response.appendInt(effect.effect);
                    AvatarEffectsComposer.this.response.appendInt(0);
                    AvatarEffectsComposer.this.response.appendInt(effect.duration > 0 ? effect.duration : Integer.MAX_VALUE);
                    AvatarEffectsComposer.this.response.appendInt((effect.duration > 0 ? (effect.total - (effect.isActivated() ? 1 : 0)) : 0));

                    if(!effect.isActivated() && effect.duration > 0) {
                        AvatarEffectsComposer.this.response.appendInt(0);
                    }
                    else {
                        AvatarEffectsComposer.this.response.appendInt(effect.duration > 0 ? (Emulator.getIntUnixTimestamp() - effect.activationTimestamp) + effect.duration : 0);
                    }
                    AvatarEffectsComposer.this.response.appendBoolean(effect.duration <= 0); // is perm
                }
            }
        }
        return this.response;
    }
}
