package com.eu.habbo.messages.outgoing.room.action;

import com.eu.habbo.habbohotel.rooms.RoomUnit;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class DanceComposer extends MessageComposer {
    private final RoomUnit roomUnit;

    public DanceComposer(RoomUnit roomUnit) {
        this.roomUnit = roomUnit;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.Dance);
        this.response.appendInt(this.roomUnit.getId());
        this.response.appendInt(this.roomUnit.getDanceType().getType());
        return this.response;
    }
}
