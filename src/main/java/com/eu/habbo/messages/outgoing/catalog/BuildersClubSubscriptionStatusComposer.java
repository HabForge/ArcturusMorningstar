package com.eu.habbo.messages.outgoing.catalog;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class BuildersClubSubscriptionStatusComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.BuildersClubSubscriptionStatus);
        this.response.appendInt(Integer.MAX_VALUE);
        this.response.appendInt(0);
        this.response.appendInt(100);
        this.response.appendInt(Integer.MAX_VALUE);
        this.response.appendInt(0);
        return this.response;
    }
}
