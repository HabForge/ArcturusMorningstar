package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.habbohotel.catalog.marketplace.MarketPlace;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.marketplace.MarketplaceCanMakeOfferResultComposer;

public class GetMarketplaceCanMakeOfferEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (MarketPlace.MARKETPLACE_ENABLED)
            this.client.sendResponse(new MarketplaceCanMakeOfferResultComposer(1, 0, 0));
        else
            this.client.sendResponse(new MarketplaceCanMakeOfferResultComposer(3, 0, 0));
    }
}
