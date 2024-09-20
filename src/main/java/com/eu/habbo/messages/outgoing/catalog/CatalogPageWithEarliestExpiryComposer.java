package com.eu.habbo.messages.outgoing.catalog;

import com.eu.habbo.habbohotel.catalog.CatalogPage;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class CatalogPageWithEarliestExpiryComposer extends MessageComposer {
    private final CatalogPage page;
    private final String image;

    public CatalogPageWithEarliestExpiryComposer(CatalogPage page, String image) {
        this.page = page;
        this.image = image;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CatalogPageWithEarliestExpiry);
        this.response.appendString(this.page.getCaption());
        this.response.appendInt(this.page.getId());
        this.response.appendString(this.image);
        return this.response;
    }
}