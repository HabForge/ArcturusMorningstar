package com.eu.habbo.messages.incoming.catalog;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.hotelview.BonusRareComposer;

public class GetBonusRareInfoEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new BonusRareComposer(this.client.getHabbo()));
    }
}
