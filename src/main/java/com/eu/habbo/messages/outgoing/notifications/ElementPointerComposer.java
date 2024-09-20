package com.eu.habbo.messages.outgoing.notifications;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ElementPointerComposer extends MessageComposer {
    private final String key;

    public ElementPointerComposer(String key) {
        this.key = key;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ElementPointer);
        this.response.appendString(this.key);
        return this.response;
    }
}