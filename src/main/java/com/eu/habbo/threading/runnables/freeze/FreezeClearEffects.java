package com.eu.habbo.threading.runnables.freeze;

import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.outgoing.room.action.AvatarEffectComposer;

public class FreezeClearEffects implements Runnable {
    private final Habbo habbo;

    public FreezeClearEffects(Habbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public void run() {
        this.habbo.getRoomUnit().setEffectId(0, 0);
        this.habbo.getRoomUnit().setCanWalk(true);
        if (this.habbo.getHabboInfo().getCurrentRoom() != null) {
            this.habbo.getHabboInfo().getCurrentRoom().sendComposer(new AvatarEffectComposer(this.habbo.getRoomUnit()).compose());
        }
    }
}
