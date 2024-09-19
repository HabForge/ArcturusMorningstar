package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.habbohotel.catalog.marketplace.MarketPlace;
import com.eu.habbo.messages.incoming.MessageHandler;

public class BuyMarketplaceOfferEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int offerId = this.packet.readInt();

        MarketPlace.buyItem(offerId, this.client);
    }
}
