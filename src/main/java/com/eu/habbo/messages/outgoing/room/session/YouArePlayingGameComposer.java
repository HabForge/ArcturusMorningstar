package com.eu.habbo.messages.outgoing.room.session;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class YouArePlayingGameComposer extends MessageComposer {
    public final boolean isPlaying;

    public YouArePlayingGameComposer(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.YouArePlayingGame);
        this.response.appendBoolean(this.isPlaying);
        return this.response;
    }
}