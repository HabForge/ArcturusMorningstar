package com.eu.habbo.messages.incoming.friendlist;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.friendlist.FriendRequestsComposer;

public class GetFriendRequestsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new FriendRequestsComposer(this.client.getHabbo()));
    }
}
