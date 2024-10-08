package com.eu.habbo.messages.incoming.friendlist;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.friendlist.FriendListFragmentComposer;
import com.eu.habbo.messages.outgoing.friendlist.MessengerInitComposer;

import java.util.ArrayList;

public class MessengerInitEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        ArrayList<ServerMessage> messages = new ArrayList<>();
        messages.add(new MessengerInitComposer(this.client.getHabbo()).compose());
        messages.addAll(FriendListFragmentComposer.getMessagesForBuddyList(this.client.getHabbo().getMessenger().getFriends().values()));
        this.client.sendResponses(messages);
    }
}
