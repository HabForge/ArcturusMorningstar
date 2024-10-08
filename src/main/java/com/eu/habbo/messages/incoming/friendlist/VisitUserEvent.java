package com.eu.habbo.messages.incoming.friendlist;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.session.RoomForwardComposer;

public class VisitUserEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().hasPermission(Permission.ACC_AMBASSADOR)) {
            String username = this.packet.readString();

            Habbo habbo = Emulator.getGameEnvironment().getHabboManager().getHabbo(username);

            if (habbo != null) {
                if (habbo.getHabboInfo().getCurrentRoom() != null) {
                    this.client.sendResponse(new RoomForwardComposer(habbo.getHabboInfo().getCurrentRoom().getId()));
                }
            }
        }
    }
}
