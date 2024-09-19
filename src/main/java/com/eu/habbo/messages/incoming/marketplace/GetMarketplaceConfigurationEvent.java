package com.eu.habbo.messages.incoming.marketplace;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.catalog.marketplace.MarketplaceConfigComposer;

public class GetMarketplaceConfigurationEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new MarketplaceConfigComposer());
    }
}
