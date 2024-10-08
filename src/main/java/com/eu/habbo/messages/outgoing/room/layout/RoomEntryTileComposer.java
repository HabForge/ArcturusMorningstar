package com.eu.habbo.messages.outgoing.room.layout;

import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class RoomEntryTileComposer extends MessageComposer {
    private final Room room;

    public RoomEntryTileComposer(Room room) {
        this.room = room;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.RoomEntryTile);
        this.response.appendInt(this.room.getLayout().getDoorX());
        this.response.appendInt(this.room.getLayout().getDoorY());
        this.response.appendInt(this.room.getLayout().getDoorDirection());
        return this.response;
    }
}
