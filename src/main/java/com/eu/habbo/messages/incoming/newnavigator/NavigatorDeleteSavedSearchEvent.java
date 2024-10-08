package com.eu.habbo.messages.incoming.newnavigator;

import com.eu.habbo.habbohotel.navigation.NavigatorSavedSearch;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.newnavigator.NavigatorSavedSearchesComposer;

public class NavigatorDeleteSavedSearchEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int searchId = this.packet.readInt();

        NavigatorSavedSearch search = null;
        for (NavigatorSavedSearch savedSearch : this.client.getHabbo().getHabboInfo().getSavedSearches()) {
            if (savedSearch.getId() == searchId) {
                search = savedSearch;
                break;
            }
        }

        if (search == null) return;

        this.client.getHabbo().getHabboInfo().deleteSavedSearch(search);

        this.client.sendResponse(new NavigatorSavedSearchesComposer(this.client.getHabbo().getHabboInfo().getSavedSearches()));
    }
}
