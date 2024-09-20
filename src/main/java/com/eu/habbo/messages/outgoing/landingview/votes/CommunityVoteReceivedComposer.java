package com.eu.habbo.messages.outgoing.landingview.votes;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class CommunityVoteReceivedComposer extends MessageComposer {
    private final boolean unknownBoolean;

    public CommunityVoteReceivedComposer(boolean unknownBoolean) {
        this.unknownBoolean = unknownBoolean;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CommunityVoteReceived);
        this.response.appendBoolean(this.unknownBoolean);
        return this.response;
    }
}