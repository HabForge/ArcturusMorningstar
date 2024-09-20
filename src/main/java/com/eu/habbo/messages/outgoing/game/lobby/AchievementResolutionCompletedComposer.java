package com.eu.habbo.messages.outgoing.game.lobby;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class AchievementResolutionCompletedComposer extends MessageComposer {
    public final String badge;

    public AchievementResolutionCompletedComposer(String badge) {
        this.badge = badge;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AchievementResolutionCompleted);
        this.response.appendString(this.badge);
        this.response.appendString(this.badge);
        return this.response;
    }
}