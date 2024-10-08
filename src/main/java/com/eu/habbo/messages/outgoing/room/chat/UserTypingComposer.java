package com.eu.habbo.messages.outgoing.room.chat;

import com.eu.habbo.habbohotel.rooms.RoomUnit;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class UserTypingComposer extends MessageComposer {
    private final RoomUnit roomUnit;
    private final boolean typing;

    public UserTypingComposer(RoomUnit roomUnit, boolean typing) {
        this.roomUnit = roomUnit;
        this.typing = typing;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.UserTyping);
        this.response.appendInt(this.roomUnit.getId());
        this.response.appendInt(this.typing ? 1 : 0);
        return this.response;
    }
}
