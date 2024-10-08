package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.habbohotel.catalog.marketplace.MarketPlace;
import com.eu.habbo.messages.incoming.MessageHandler;

public class CancelMarketplaceOfferEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int offerId = this.packet.readInt();
        MarketPlace.takeBackItem(this.client.getHabbo(), offerId);
    }
}
