package com.eu.habbo.messages.outgoing.help;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GuideTicketCreationResultComposer extends MessageComposer {
    public static final int RECEIVED = 0;
    public static final int IGNORED = 1;
    public static final int NO_CHAT = 2;
    public static final int ALREADY_REPORTED = 3;
    public static final int NO_MISSUSE = 4;

    private final int code;

    public GuideTicketCreationResultComposer(int code) {
        this.code = code;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GuideTicketCreationResult);
        this.response.appendInt(this.code);
        return this.response;
    }
}
