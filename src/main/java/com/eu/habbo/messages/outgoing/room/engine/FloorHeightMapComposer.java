package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class FloorHeightMapComposer extends MessageComposer {
    private final Room room;

    public FloorHeightMapComposer(Room room) {
        this.room = room;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FloorHeightMap);
        this.response.appendBoolean(true);
        this.response.appendInt(this.room.getWallHeight()); //FixedWallsHeight
        this.response.appendString(this.room.getLayout().getRelativeMap());
        this.response.appendInt(0); // TODO(HabForge): Area hide data
        // Array of AreaHideMessageData
        // - furniId: int
        // - on: bool
        // - rootX: int
        // - rootY: int
        // - width: int
        // - length: int
        // - invert: bool
        return this.response;
    }
}
