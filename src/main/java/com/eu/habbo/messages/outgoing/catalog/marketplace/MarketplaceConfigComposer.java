package com.eu.habbo.messages.outgoing.catalog.marketplace;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class MarketplaceConfigComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.MarketplaceConfiguration);
        this.response.appendBoolean(true); // isEnabled
        this.response.appendInt(1); // commission
        this.response.appendInt(10); // tokenBatchPrice
        this.response.appendInt(5); // tokensBatchSize
        this.response.appendInt(1); // offerMinPrice
        this.response.appendInt(1000000); // offerMaxPrice
        this.response.appendInt(48); // expirationHours
        this.response.appendInt(7); // averagePricePeriod
        this.response.appendInt(0); // sellingFeePercentage
        this.response.appendInt(0); // revenueLimit
        this.response.appendInt(0); // halfTaxLimit
        return this.response;
    }
}
