package com.eu.habbo.messages.outgoing.catalog;

import com.eu.habbo.habbohotel.catalog.CatalogItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class SeasonalCalendarDailyOfferComposer extends MessageComposer {
    private final int pageId;
    private final CatalogItem catalogItem;

    public SeasonalCalendarDailyOfferComposer(int pageId, CatalogItem catalogItem) {
        this.pageId = pageId;
        this.catalogItem = catalogItem;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.SeasonalCalendarDailyOffer);
        this.response.appendInt(this.pageId);
        this.catalogItem.serialize(this.response);
        return this.response;
    }
}