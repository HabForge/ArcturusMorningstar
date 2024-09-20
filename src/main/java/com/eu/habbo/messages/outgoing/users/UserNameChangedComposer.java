package com.eu.habbo.messages.outgoing.users;

import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class UserNameChangedComposer extends MessageComposer {

    private final int userId;
    private final int roomId;
    private final String name;

    public UserNameChangedComposer(Habbo habbo) {
        this(habbo, false);
    }

    public UserNameChangedComposer(Habbo habbo, boolean includePrefix) {
        this.userId = habbo.getHabboInfo().getId();
        this.roomId = habbo.getRoomUnit().getId();
        this.name = (includePrefix ? Room.PREFIX_FORMAT.replace("%color%", habbo.getHabboInfo().getRank().getPrefixColor()).replace("%prefix%", habbo.getHabboInfo().getRank().getPrefix()) : "") + habbo.getHabboInfo().getUsername();
    }

    public UserNameChangedComposer(int userId, int roomId, String name) {
        this.userId = userId;
        this.roomId = roomId;
        this.name = name;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.UserNameChanged);
        this.response.appendInt(this.userId);
        this.response.appendInt(this.roomId);
        this.response.appendString(this.name);
        return this.response;
    }
}