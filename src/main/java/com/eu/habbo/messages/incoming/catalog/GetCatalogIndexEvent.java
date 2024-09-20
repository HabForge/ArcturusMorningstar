package com.eu.habbo.messages.incoming.catalog;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.catalog.BuildersClubFurniCountComposer;
import com.eu.habbo.messages.outgoing.catalog.CatalogIndexComposer;

public class GetCatalogIndexEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {

        String MODE = this.packet.readString();
        if (MODE.equalsIgnoreCase("normal")) {
            this.client.sendResponse(new BuildersClubFurniCountComposer(0));
            this.client.sendResponse(new CatalogIndexComposer(this.client.getHabbo(), MODE));
        } else {
            this.client.sendResponse(new BuildersClubFurniCountComposer(1));
            this.client.sendResponse(new CatalogIndexComposer(this.client.getHabbo(), MODE));
        }

    }
}
