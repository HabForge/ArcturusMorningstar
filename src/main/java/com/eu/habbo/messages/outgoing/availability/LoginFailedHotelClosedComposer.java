package com.eu.habbo.messages.outgoing.availability;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class LoginFailedHotelClosedComposer extends MessageComposer {
    private final int hour;
    private final int minute;

    public LoginFailedHotelClosedComposer(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.LoginFailedHotelClosed);
        this.response.appendInt(this.hour);
        this.response.appendInt(this.minute);
        return this.response;
    }
}
