package com.eu.habbo.messages.outgoing.mysterybox;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class CancelMysteryBoxWaitComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CancelMysteryBoxWait);
        return this.response;
    }
}
