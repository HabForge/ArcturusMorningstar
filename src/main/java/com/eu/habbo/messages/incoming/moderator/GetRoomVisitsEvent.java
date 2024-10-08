package com.eu.habbo.messages.incoming.moderator;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.users.HabboInfo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.moderation.RoomVisitsComposer;

public class GetRoomVisitsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().hasPermission(Permission.ACC_SUPPORTTOOL)) {
            int userId = this.packet.readInt();

            HabboInfo habboInfo = Emulator.getGameEnvironment().getHabboManager().getHabboInfo(userId);

            if (habboInfo != null) {
                this.client.sendResponse(new RoomVisitsComposer(habboInfo, Emulator.getGameEnvironment().getModToolManager().getUserRoomVisits(userId)));
            }
        }
    }
}
