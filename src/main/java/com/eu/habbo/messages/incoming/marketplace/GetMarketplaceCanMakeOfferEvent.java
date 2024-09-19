package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.habbohotel.catalog.marketplace.MarketPlace;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.catalog.marketplace.MarketplaceSellItemComposer;

public class GetMarketplaceCanMakeOfferEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (MarketPlace.MARKETPLACE_ENABLED)
            this.client.sendResponse(new MarketplaceSellItemComposer(1, 0, 0));
        else
            this.client.sendResponse(new MarketplaceSellItemComposer(3, 0, 0));
    }
}
