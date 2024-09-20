package com.eu.habbo.messages.outgoing.help;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class CallForHelpDisabledNotifyComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CallForHelpDisabledNotify);
        this.response.appendString("");
        return this.response;
    }
}
