package com.eu.habbo.messages.incoming.inventory.badges;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.IgnoredUsersComposer;

public class GetBadgePointLimitsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new IgnoredUsersComposer());
    }
}
