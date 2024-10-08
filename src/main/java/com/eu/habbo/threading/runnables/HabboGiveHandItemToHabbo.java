package com.eu.habbo.threading.runnables;

import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.outgoing.room.action.CarryObjectComposer;
import com.eu.habbo.messages.outgoing.users.HandItemReceivedComposer;

public class HabboGiveHandItemToHabbo implements Runnable {
    private final Habbo target;
    private final Habbo from;

    public HabboGiveHandItemToHabbo(Habbo from, Habbo target) {
        this.target = target;
        this.from = from;
    }

    @Override
    public void run() {
        if (this.from.getHabboInfo().getCurrentRoom() == null || this.target.getHabboInfo().getCurrentRoom() == null)
            return;

        if (this.from.getHabboInfo().getCurrentRoom() != this.target.getHabboInfo().getCurrentRoom())
            return;

        int itemId = this.from.getRoomUnit().getHandItem();

        if (itemId > 0) {
            this.from.getRoomUnit().setHandItem(0);
            this.from.getHabboInfo().getCurrentRoom().sendComposer(new CarryObjectComposer(this.from.getRoomUnit()).compose());
            this.target.getRoomUnit().lookAtPoint(this.from.getRoomUnit().getCurrentLocation());
            this.target.getRoomUnit().statusUpdate(true);
            this.target.getClient().sendResponse(new HandItemReceivedComposer(this.from.getRoomUnit(), itemId));
            this.target.getRoomUnit().setHandItem(itemId);
            this.target.getHabboInfo().getCurrentRoom().sendComposer(new CarryObjectComposer(this.target.getRoomUnit()).compose());
        }
    }
}
