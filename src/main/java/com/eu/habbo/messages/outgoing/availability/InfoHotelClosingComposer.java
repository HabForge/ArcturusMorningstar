package com.eu.habbo.messages.outgoing.availability;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class InfoHotelClosingComposer extends MessageComposer {
    private final int minutes;

    public InfoHotelClosingComposer(int minutes) {
        this.minutes = minutes;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.InfoHotelClosing);
        this.response.appendInt(this.minutes);
        return this.response;
    }
}
