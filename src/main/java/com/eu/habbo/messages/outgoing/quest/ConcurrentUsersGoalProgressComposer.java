package com.eu.habbo.messages.outgoing.quest;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ConcurrentUsersGoalProgressComposer extends MessageComposer {
    public static final int ACTIVE = 0;
    public static final int HIDDEN = 2;
    public static final int ACHIEVED = 3;

    private final int state;
    private final int userCount;
    private final int goal;

    public ConcurrentUsersGoalProgressComposer(int state, int userCount, int goal) {
        this.state = state;
        this.userCount = userCount;
        this.goal = goal;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ConcurrentUsersGoalProgress);
        this.response.appendInt(this.state);
        this.response.appendInt(this.userCount);
        this.response.appendInt(this.goal);
        return this.response;
    }
}
