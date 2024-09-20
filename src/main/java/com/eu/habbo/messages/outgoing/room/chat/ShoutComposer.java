package com.eu.habbo.messages.outgoing.room.chat;

import com.eu.habbo.habbohotel.rooms.RoomChatMessage;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ShoutComposer extends MessageComposer {
    private RoomChatMessage roomChatMessage;

    public ShoutComposer(RoomChatMessage roomChatMessage) {
        this.roomChatMessage = roomChatMessage;
    }

    @Override
    protected ServerMessage composeInternal() {
        if (this.roomChatMessage.getMessage().isEmpty())
            return null;

        this.response.init(Outgoing.Shout);
        this.roomChatMessage.serialize(this.response);

        return this.response;
    }
}
