package com.eu.habbo.messages.outgoing.navigator;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

import java.util.Set;

public class PopularRoomTagsResultComposer extends MessageComposer {
    private final Set<String> tags;

    public PopularRoomTagsResultComposer(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.PopularRoomTagsResult);
        this.response.appendInt(this.tags.size());

        int i = 1;
        for (String s : this.tags) {
            this.response.appendString(s);
            this.response.appendInt(i);
            i++;
        }

        return this.response;
    }
}
