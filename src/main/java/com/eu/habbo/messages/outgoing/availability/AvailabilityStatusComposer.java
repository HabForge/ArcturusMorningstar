package com.eu.habbo.messages.outgoing.availability;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class AvailabilityStatusComposer extends MessageComposer {
    private final boolean isOpen;
    private final boolean isShuttingDown;
    private final boolean isAuthenticHabbo;

    public AvailabilityStatusComposer(boolean isOpen, boolean isShuttingDown, boolean isAuthenticHabbo) {
        this.isOpen = isOpen;
        this.isShuttingDown = isShuttingDown;
        this.isAuthenticHabbo = isAuthenticHabbo;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AvailabilityStatus);

        this.response.appendBoolean(isOpen);//isOpen
        this.response.appendBoolean(isShuttingDown);//onShutdown
        this.response.appendBoolean(isAuthenticHabbo);//isAuthenticHabbo
        return this.response;
    }
}
