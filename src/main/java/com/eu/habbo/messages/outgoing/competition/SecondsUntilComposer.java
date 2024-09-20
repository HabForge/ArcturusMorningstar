package com.eu.habbo.messages.outgoing.competition;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class SecondsUntilComposer extends MessageComposer {
    private final String dateString;
    private final int seconds;

    public SecondsUntilComposer(String dateString, int seconds) {
        this.dateString = dateString;
        this.seconds = seconds;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.SecondsUntil);
        this.response.appendString(this.dateString);
        this.response.appendInt(this.seconds);

        return this.response;
    }
}
