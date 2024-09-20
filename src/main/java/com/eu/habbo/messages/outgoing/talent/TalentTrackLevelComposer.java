package com.eu.habbo.messages.outgoing.talent;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class TalentTrackLevelComposer extends MessageComposer {
    private final String name;

    public TalentTrackLevelComposer(String name) {
        this.name = name;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.TalentTrackLevel);
        this.response.appendString(this.name);
        this.response.appendInt(4);
        this.response.appendInt(4);
        return this.response;
    }
}
