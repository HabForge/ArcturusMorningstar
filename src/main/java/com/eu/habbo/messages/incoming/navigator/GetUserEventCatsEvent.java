package com.eu.habbo.messages.incoming.navigator;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.newnavigator.NewNavigatorPreferencesComposer;

public class GetUserEventCatsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {


        this.client.sendResponse(new NewNavigatorPreferencesComposer(this.client.getHabbo().getHabboStats().navigatorWindowSettings));

    }
}
