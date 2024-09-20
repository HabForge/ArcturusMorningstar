package com.eu.habbo.messages.outgoing.notifications;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class InfoFeedEnableComposer extends MessageComposer {
    private final boolean enabled;

    public InfoFeedEnableComposer(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.InfoFeedEnable);
        this.response.appendBoolean(this.enabled);
        return this.response;
    }
}
