package com.eu.habbo.messages.incoming.moderator;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.modtool.ScripterManager;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.moderation.ModeratorRoomInfoComposer;

public class GetModeratorRoomInfoEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().hasPermission(Permission.ACC_SUPPORTTOOL)) {
            int roomId = this.packet.readInt();

            Room room = Emulator.getGameEnvironment().getRoomManager().getRoom(roomId);

            if (room != null) {
                this.client.sendResponse(new ModeratorRoomInfoComposer(room));
            }
        } else {
            ScripterManager.scripterDetected(this.client, Emulator.getTexts().getValue("scripter.warning.modtools.roominfo").replace("%username%", this.client.getHabbo().getHabboInfo().getUsername()));
        }
    }
}
