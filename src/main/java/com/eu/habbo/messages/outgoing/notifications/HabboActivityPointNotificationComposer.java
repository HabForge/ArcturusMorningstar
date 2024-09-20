package com.eu.habbo.messages.outgoing.notifications;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class HabboActivityPointNotificationComposer extends MessageComposer {
    private final int currentAmount;
    private final int amountAdded;
    private final int type;

    public HabboActivityPointNotificationComposer(int currentAmount, int amountAdded, int type) {
        this.currentAmount = currentAmount;
        this.amountAdded = amountAdded;
        this.type = type;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.HabboActivityPointNotification);
        this.response.appendInt(this.currentAmount);
        this.response.appendInt(this.amountAdded);
        this.response.appendInt(this.type);
        return this.response;
    }
}
