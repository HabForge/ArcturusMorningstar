package com.eu.habbo.habbohotel.items.interactions;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.items.Item;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.wired.WiredEffectType;
import com.eu.habbo.messages.ClientMessage;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.room.engine.ObjectDataUpdateComposer;
import com.eu.habbo.messages.outgoing.room.furniture.AreaHideMessageComposer;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InteractionAreaHider extends InteractionDefault {
    private static final Logger LOGGER = LoggerFactory.getLogger(InteractionAreaHider.class);
    private static final Gson gson = new Gson();

    public InteractionAreaHider(ResultSet set, Item baseItem) throws SQLException {
        super(set, baseItem);
    }

    public InteractionAreaHider(int id, int userId, Item item, String extradata, int limitedStack, int limitedSells) {
        super(id, userId, item, extradata, limitedStack, limitedSells);
    }

    @Override
    public void onPlace(Room room) {
        super.onPlace(room);
        JsonData data = new JsonData();

        updateExtradata(data);
    }

    @Override
    public void onClick(GameClient client, Room room, Object[] objects) throws Exception {
        super.onClick(client, room, objects);

        if ((objects.length >= 2 && objects[1] instanceof WiredEffectType) || (client != null && room.hasRights(client.getHabbo()))) {
            JsonData data = getExtradataAsJson();

            if (data != null) {
                data.on = 1 - data.on;
                updateExtradata(data);
                room.toggleAreaHiderItemsVisibility();
                room.sendComposer(new AreaHideMessageComposer(this).compose());
                room.sendComposer(new ObjectDataUpdateComposer(this).compose());
            }
        }

    }

    @Override
    public void serializeExtradata(ServerMessage serverMessage) {
        JsonData data = getExtradataAsJson();

        if (data == null) {
            data = new JsonData();
        }

        serverMessage.appendInt(5);  // itemType
        serverMessage.appendInt(8);  // length of int

        serverMessage.appendInt(data.on);
        serverMessage.appendInt(data.rootX);
        serverMessage.appendInt(data.rootY);
        serverMessage.appendInt(data.width);
        serverMessage.appendInt(data.length);
        serverMessage.appendInt(data.invisibility ? 1 : 0);
        serverMessage.appendInt(data.wallItemsEnabled ? 1 : 0);
        serverMessage.appendInt(data.invertEnabled ? 1 : 0);
    }

    public void saveData(ClientMessage message) {

        JsonData data = new JsonData(
                0,
                message.readInt(),
                message.readInt(),
                message.readInt(),
                message.readInt(),
                message.readBoolean(),
                message.readBoolean(),
                message.readBoolean()
        );

        updateExtradata(data);
    }


    private JsonData getExtradataAsJson() {
        String jsonString = this.getExtradata();
        if (jsonString != null && jsonString.trim().startsWith("{")) {
            try {
                return gson.fromJson(jsonString, JsonData.class);
            } catch (JsonSyntaxException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }


    private void updateExtradata(JsonData data) {
        this.setExtradata(gson.toJson(data));
        this.needsUpdate(true);
        Emulator.getThreading().run(this);
    }

    public static class JsonData {
        public int on;
        public int rootX;
        public int rootY;
        public int width;
        public int length;
        public boolean invisibility;
        public boolean wallItemsEnabled;
        public boolean invertEnabled;

        public JsonData() {
            this.on = 0;
            this.rootX = 0;
            this.rootY = 0;
            this.width = 0;
            this.length = 0;
            this.invisibility = false;
            this.wallItemsEnabled = false;
            this.invertEnabled = false;
        }

        public JsonData(int on, int rootX, int rootY, int width, int length, boolean invisibility, boolean wallItemsEnabled, boolean invertEnabled) {
            this.on = on;
            this.rootX = rootX;
            this.rootY = rootY;
            this.width = width;
            this.length = length;
            this.invisibility = invisibility;
            this.wallItemsEnabled = wallItemsEnabled;
            this.invertEnabled = invertEnabled;
        }
    }
}