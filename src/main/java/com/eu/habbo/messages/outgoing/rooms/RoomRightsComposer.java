package com.eu.habbo.messages.outgoing.rooms;

import com.eu.habbo.habbohotel.rooms.RoomRightLevels;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import com.eu.habbo.protocol.Revision;

public class RoomRightsComposer extends MessageComposer {
    private final Revision revision;
    private final int flatId;
    private final RoomRightLevels type;

    public RoomRightsComposer(Revision revision, int flatId, RoomRightLevels type) {
        this.revision = revision;
        this.flatId = flatId;
        this.type = type;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.YouAreController);

        if (this.revision == Revision.PRODUCTION_201611291003_338511768) {
            this.response.appendInt(this.type.level);
        } else {
            this.response.appendInt(this.flatId);
            this.response.appendInt(this.type.level);
        }

        return this.response;
    }
}
