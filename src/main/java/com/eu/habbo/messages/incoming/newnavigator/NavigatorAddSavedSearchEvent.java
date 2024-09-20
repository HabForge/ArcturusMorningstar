package com.eu.habbo.messages.incoming.newnavigator;

import com.eu.habbo.habbohotel.navigation.NavigatorSavedSearch;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.newnavigator.NavigatorSavedSearchesComposer;

public class NavigatorAddSavedSearchEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        String searchCode = this.packet.readString();
        String filter = this.packet.readString();

        if (searchCode.length() > 255) searchCode = searchCode.substring(0, 255);
        if (filter.length() > 255) filter = filter.substring(0, 255);

        this.client.getHabbo().getHabboInfo().addSavedSearch(new NavigatorSavedSearch(searchCode, filter));

        this.client.sendResponse(new NavigatorSavedSearchesComposer(this.client.getHabbo().getHabboInfo().getSavedSearches()));
    }
}
