package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.marketplace.MarketPlaceOwnOffersComposer;

public class GetMarketplaceOwnOffersEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new MarketPlaceOwnOffersComposer(this.client.getHabbo()));
    }
}
