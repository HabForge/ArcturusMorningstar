package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.habbohotel.rooms.RoomUnit;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class UserRemoveComposer extends MessageComposer {
    private final RoomUnit roomUnit;

    public UserRemoveComposer(RoomUnit roomUnit) {
        this.roomUnit = roomUnit;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.UserRemove);
        this.response.appendString(this.roomUnit.getId() + "");
        return this.response;
    }
}
