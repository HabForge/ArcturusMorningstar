package com.eu.habbo.messages.outgoing.room.action;

import com.eu.habbo.habbohotel.rooms.RoomUnit;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class CarryObjectComposer extends MessageComposer {
    private final RoomUnit roomUnit;

    public CarryObjectComposer(RoomUnit roomUnit) {
        this.roomUnit = roomUnit;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CarryObject);
        this.response.appendInt(this.roomUnit.getId());
        this.response.appendInt(this.roomUnit.getHandItem());
        return this.response;
    }
}
