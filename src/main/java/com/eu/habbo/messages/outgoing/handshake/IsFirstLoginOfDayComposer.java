package com.eu.habbo.messages.outgoing.handshake;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class IsFirstLoginOfDayComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.IsFirstLoginOfDay);
        this.response.appendBoolean(false); //Think something related to promo. Not sure though.
        return this.response;
    }
}
