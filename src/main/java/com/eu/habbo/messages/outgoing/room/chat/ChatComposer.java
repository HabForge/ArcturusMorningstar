package com.eu.habbo.messages.outgoing.room.chat;

import com.eu.habbo.habbohotel.rooms.RoomChatMessage;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class ChatComposer extends MessageComposer {
    private final RoomChatMessage roomChatMessage;

    public ChatComposer(RoomChatMessage roomChatMessage) {
        this.roomChatMessage = roomChatMessage;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.Chat);

        if (this.roomChatMessage.getMessage().isEmpty())
            return null;

        this.roomChatMessage.serialize(this.response);
        return this.response;
    }
}
