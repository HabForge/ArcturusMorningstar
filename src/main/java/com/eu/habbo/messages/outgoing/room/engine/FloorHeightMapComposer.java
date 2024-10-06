package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.items.interactions.InteractionAreaHider;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FloorHeightMapComposer extends MessageComposer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FloorHeightMapComposer.class);

    private final Room room;

    public FloorHeightMapComposer(Room room) {
        this.room = room;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FloorHeightMap);
        this.response.appendBoolean(true);
        this.response.appendInt(this.room.getWallHeight());
        this.response.appendString(this.room.getLayout().getRelativeMap());

        List<HabboItem> areaHiders = this.room.getAreaHiders(true);
        this.response.appendInt(areaHiders.size());

        for (HabboItem areaHider : areaHiders) {
            InteractionAreaHider.JsonData data = InteractionAreaHider.parseExtradata(areaHider);

            this.response.appendInt(areaHider.getId());
            this.response.appendBoolean(data.on);
            this.response.appendInt(data.rootX);
            this.response.appendInt(data.rootY);
            this.response.appendInt(data.width);
            this.response.appendInt(data.length);
            this.response.appendBoolean(data.invertEnabled);
        }

        return this.response;
    }
}
