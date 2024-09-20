package com.eu.habbo.messages.outgoing.notifications;

import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class HabboBroadcastComposer extends MessageComposer {
    private final String message;

    public HabboBroadcastComposer(String message) {
        this.message = message;
    }

    public HabboBroadcastComposer(String message, Habbo habbo) {
        this.message = message.replace("%username%", habbo.getHabboInfo().getUsername());
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.HabboBroadcast);

        this.response.appendString(this.message);

        return this.response;
    }
}
