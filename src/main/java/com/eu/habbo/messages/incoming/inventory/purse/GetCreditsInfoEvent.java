package com.eu.habbo.messages.incoming.inventory.purse;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.inventory.purse.CreditBalanceComposer;
import com.eu.habbo.messages.outgoing.notifications.ActivityPointsComposer;

public class GetCreditsInfoEvent extends MessageHandler {
    @Override
    public void handle() {
        this.client.sendResponse(new CreditBalanceComposer(this.client.getHabbo()));
        this.client.sendResponse(new ActivityPointsComposer(this.client.getHabbo()));
    }
}
