package com.eu.habbo.messages.outgoing.notifications;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ClubGiftNotificationComposer extends MessageComposer {
    private final int count;

    public ClubGiftNotificationComposer(int count) {
        this.count = count;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ClubGiftNotification);
        this.response.appendInt(this.count);
        return this.response;
    }
}
