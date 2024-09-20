package com.eu.habbo.messages.outgoing.help;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GuideTicketResolutionComposer extends MessageComposer {
    public final static int CLOSED = 1;
    public final static int MISUSE = 2;

    public final int code;

    public GuideTicketResolutionComposer(int code) {
        this.code = code;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GuideTicketResolution);
        this.response.appendInt(this.code);
        return this.response;
    }
}
