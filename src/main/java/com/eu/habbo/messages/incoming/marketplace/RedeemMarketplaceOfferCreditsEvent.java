package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.habbohotel.catalog.marketplace.MarketPlace;
import com.eu.habbo.messages.incoming.MessageHandler;

public class RedeemMarketplaceOfferCreditsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        MarketPlace.getCredits(this.client);
    }
}
