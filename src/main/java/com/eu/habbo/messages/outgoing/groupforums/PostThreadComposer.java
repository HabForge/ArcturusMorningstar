package com.eu.habbo.messages.outgoing.groupforums;

import com.eu.habbo.habbohotel.guilds.forums.ForumThread;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class PostThreadComposer extends MessageComposer {
    public final ForumThread thread;

    public PostThreadComposer(ForumThread thread) {
        this.thread = thread;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.PostThread);
        this.response.appendInt(this.thread.getGuildId());
        this.thread.serialize(this.response);
        return this.response;
    }
}