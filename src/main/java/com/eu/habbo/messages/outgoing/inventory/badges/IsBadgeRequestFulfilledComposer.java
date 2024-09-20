package com.eu.habbo.messages.outgoing.inventory.badges;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class IsBadgeRequestFulfilledComposer extends MessageComposer {
    private final String badge;
    private final boolean enabled;

    public IsBadgeRequestFulfilledComposer(String badge, boolean enabled) {
        this.badge = badge;
        this.enabled = enabled;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.IsBadgeRequestFulfilled);
        this.response.appendString(this.badge);
        this.response.appendBoolean(this.enabled);
        return this.response;
    }
}