package com.eu.habbo.messages.outgoing.groupforums;

import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

import java.util.Iterator;
import java.util.Set;

public class ForumsListComposer extends MessageComposer {
    private final Set<Guild> guilds;
    private final Habbo habbo;
    private final int mode;
    private final int index;

    public ForumsListComposer(Set<Guild> guilds, Habbo habbo, int mode, int index) {
        this.guilds = guilds;
        this.habbo = habbo;
        this.mode = mode;
        this.index = index;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ForumsList);
        this.response.appendInt(this.mode);
        this.response.appendInt(this.guilds.size());

        this.response.appendInt(this.index);

        Iterator<Guild> it = guilds.iterator();
        int count = guilds.size() > 20 ? 20 : guilds.size();

        this.response.appendInt(count);

        for (int i = 0; i < index; i++) {
            if (!it.hasNext())
                break;

            it.next();
        }

        for (int i = 0; i < count; i++) {
            if (!it.hasNext())
                break;

            ForumDataComposer.serializeForumData(this.response, it.next(), habbo);
        }

        return this.response;
    }
}