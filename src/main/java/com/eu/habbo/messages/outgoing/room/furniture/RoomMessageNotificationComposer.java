package com.eu.habbo.messages.outgoing.room.furniture;

import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class RoomMessageNotificationComposer extends MessageComposer {
    private final Room room;
    private final int count;

    public RoomMessageNotificationComposer(Room room, int count) {
        this.room = room;
        this.count = count;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.RoomMessageNotification);
        this.response.appendInt(this.room.getId());
        this.response.appendString(this.room.getName());
        this.response.appendInt(this.count);
        return this.response;
    }
}