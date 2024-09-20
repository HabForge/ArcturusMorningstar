package com.eu.habbo.messages.outgoing.advertisement;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class InterstitialComposer extends MessageComposer {
    private final boolean unknownBoolean;

    public InterstitialComposer(boolean unknownBoolean) {
        this.unknownBoolean = unknownBoolean;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.Interstitial);
        this.response.appendBoolean(this.unknownBoolean);
        return this.response;
    }
}