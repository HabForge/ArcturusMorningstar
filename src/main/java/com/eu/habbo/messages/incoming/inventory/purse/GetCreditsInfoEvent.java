package com.eu.habbo.messages.incoming.inventory.purse;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.UserCreditsComposer;
import com.eu.habbo.messages.outgoing.users.UserCurrencyComposer;

public class GetCreditsInfoEvent extends MessageHandler {
    @Override
    public void handle() {
        this.client.sendResponse(new UserCreditsComposer(this.client.getHabbo()));
        this.client.sendResponse(new UserCurrencyComposer(this.client.getHabbo()));
    }
}
