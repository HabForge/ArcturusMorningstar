package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.marketplace.MarketplaceItemStatsComposer;

public class GetMarketplaceItemStatsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.packet.readInt();
        int id = this.packet.readInt();

        this.client.sendResponse(new MarketplaceItemStatsComposer(id));
    }
}
