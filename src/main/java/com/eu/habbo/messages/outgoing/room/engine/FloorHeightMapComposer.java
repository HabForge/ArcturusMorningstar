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
        Gson gson = new Gson();

        for (HabboItem areaHider : areaHiders) {
            String extraData = areaHider.getExtradata();

            if (extraData != null && extraData.trim().startsWith("{")) {
                try {
                    InteractionAreaHider.JsonData data = gson.fromJson(extraData, InteractionAreaHider.JsonData.class);

                    this.response.appendInt(areaHider.getId());
                    this.response.appendBoolean(data.on == 1);
                    this.response.appendInt(data.rootX);
                    this.response.appendInt(data.rootY);
                    this.response.appendInt(data.width);
                    this.response.appendInt(data.length);
                    this.response.appendBoolean(data.invertEnabled);

                } catch (JsonSyntaxException e) {
                    LOGGER.error("Error upon parsing extradata of AreaHider({}), error: {}", areaHider.getId(), e.getMessage());
                }
            }
        }

        return this.response;
    }
}
