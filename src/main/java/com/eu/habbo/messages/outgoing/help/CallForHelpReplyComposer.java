package com.eu.habbo.messages.outgoing.help;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class CallForHelpReplyComposer extends MessageComposer {
    private final String message;

    public CallForHelpReplyComposer(String message) {
        this.message = message;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CallForHelpReply);
        this.response.appendString(this.message);
        return this.response;
    }
}
