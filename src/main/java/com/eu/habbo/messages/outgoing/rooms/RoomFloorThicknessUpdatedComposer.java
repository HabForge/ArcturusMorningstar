package com.eu.habbo.messages.outgoing.rooms;

import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;

public class RoomFloorThicknessUpdatedComposer extends MessageComposer {
    private final Room room;

    public RoomFloorThicknessUpdatedComposer(Room room) {
        this.room = room;
    }

    @Override
    protected ServerMessage composeInternal() {
        // TODO(HabForge): Debug
        throw new UnsupportedOperationException("Fix packet.");

        //this.response.init(Outgoing.RoomFloorThicknessUpdatedComposer);
        //this.response.appendBoolean(this.room.isHideWall()); //Hide walls?
        //this.response.appendInt(this.room.getFloorSize()); //Floor Thickness
        //this.response.appendInt(this.room.getWallSize()); //Wall Thickness
        //return this.response;
    }
}