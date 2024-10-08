package com.eu.habbo.messages.outgoing.catalog;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class PurchaseNotAllowedComposer extends MessageComposer {
    public final static int ILLEGAL = 0;
    public final static int REQUIRES_CLUB = 1;

    private final int code;

    public PurchaseNotAllowedComposer(int code) {
        this.code = code;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.PurchaseNotAllowed);
        this.response.appendInt(this.code);
        return this.response;
    }
}
