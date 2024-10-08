package com.eu.habbo.messages.outgoing.newnavigator;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class NavigatorLiftedRoomsComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.NavigatorLiftedRooms);
        this.response.appendInt(0);
        return this.response;
    }
}
