package com.eu.habbo.messages.outgoing.avatar;

import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ChangeUserNameResultComposer extends MessageComposer {
    private final Habbo habbo;

    public ChangeUserNameResultComposer(Habbo habbo) {
        this.habbo = habbo;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ChangeUserNameResult);
        this.response.appendInt(0);
        this.response.appendString(this.habbo.getHabboInfo().getUsername());
        this.response.appendInt(0);
        return this.response;
    }
}
