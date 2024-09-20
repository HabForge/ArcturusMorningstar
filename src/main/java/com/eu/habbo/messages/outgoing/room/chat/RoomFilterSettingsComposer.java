package com.eu.habbo.messages.outgoing.room.chat;

import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class RoomFilterSettingsComposer extends MessageComposer {
    private final Room room;

    public RoomFilterSettingsComposer(Room room) {
        this.room = room;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.RoomFilterSettings);

        this.response.appendInt(this.room.getWordFilterWords().size());

        for (String string : this.room.getWordFilterWords()) {
            this.response.appendString(string);
        }

        return this.response;
    }
}
