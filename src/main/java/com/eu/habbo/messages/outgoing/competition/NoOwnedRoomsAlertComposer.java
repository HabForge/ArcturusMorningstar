package com.eu.habbo.messages.outgoing.competition;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class NoOwnedRoomsAlertComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.NoOwnedRoomsAlert);
        return this.response;
    }
}
