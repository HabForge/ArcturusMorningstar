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
    private static final Logger LOGGER = LoggerFactory.getLogger(AreaHideMessageComposer.class);

    private final HabboItem item;
    private static final Gson gson = new Gson();

    public AreaHideMessageComposer(HabboItem item) {
        this.item = item;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AreaHide);
        this.response.appendInt(this.item.getId());

        InteractionAreaHider.JsonData data = parseExtradata(this.item.getExtradata());

        if (data != null) {
            this.response.appendBoolean(data.on == 1);
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

    private InteractionAreaHider.JsonData parseExtradata(String extradata) {
        if (extradata != null && extradata.trim().startsWith("{")) {
            try {
                return gson.fromJson(extradata, InteractionAreaHider.JsonData.class);
            } catch (JsonSyntaxException e) {
                LOGGER.error("Error upon parsing extradata {}", e.getMessage());
            }
        }
        return null;
    }
}