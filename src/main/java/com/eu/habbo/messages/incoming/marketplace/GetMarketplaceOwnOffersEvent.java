package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.catalog.marketplace.MarketplaceOwnItemsComposer;

public class GetMarketplaceOwnOffersEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new MarketplaceOwnItemsComposer(this.client.getHabbo()));
    }
}
