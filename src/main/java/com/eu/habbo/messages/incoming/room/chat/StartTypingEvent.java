package com.eu.habbo.messages.incoming.room.chat;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.chat.UserTypingComposer;

public class StartTypingEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() == null) {
            return;
        }

        if (this.client.getHabbo().getRoomUnit() == null) {
            return;
        }

        this.client.getHabbo().getHabboInfo().getCurrentRoom().sendComposer(new UserTypingComposer(this.client.getHabbo().getRoomUnit(), true).compose());
    }
}
