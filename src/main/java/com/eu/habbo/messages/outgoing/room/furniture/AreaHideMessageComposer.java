package com.eu.habbo.messages.outgoing.room.furniture;

import com.eu.habbo.habbohotel.items.interactions.InteractionAreaHider;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AreaHideMessageComposer extends MessageComposer {

    private final HabboItem item;

    public AreaHideMessageComposer(HabboItem item) {
        this.item = item;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AreaHide);
        this.response.appendInt(this.item.getId());

        InteractionAreaHider.JsonData data = InteractionAreaHider.parseExtradata(item);

        if (data != null) {
            this.response.appendBoolean(data.on);
            this.response.appendInt(data.rootX);
            this.response.appendInt(data.rootY);
            this.response.appendInt(data.width);
            this.response.appendInt(data.length);
            this.response.appendBoolean(data.invertEnabled);
        } else {
            this.response.appendInt(0);
            this.response.appendInt(0);
            this.response.appendInt(0);
            this.response.appendInt(0);
            this.response.appendInt(0);
            this.response.appendBoolean(false);
        }

        return this.response;
    }
}