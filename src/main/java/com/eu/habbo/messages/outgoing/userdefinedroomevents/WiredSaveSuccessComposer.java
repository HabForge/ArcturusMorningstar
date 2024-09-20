package com.eu.habbo.messages.outgoing.userdefinedroomevents;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class WiredSaveSuccessComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.WiredSaveSuccess);
        return this.response;
    }
}
