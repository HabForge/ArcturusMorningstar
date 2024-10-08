package com.eu.habbo.messages.incoming.room.action;

import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.rooms.RoomRightLevels;
import com.eu.habbo.habbohotel.rooms.RoomUnitStatus;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.permissions.YouAreControllerComposer;
import com.eu.habbo.messages.outgoing.roomsettings.NoSuchFlatComposer;
import gnu.trove.procedure.TIntProcedure;

public class RemoveAllRightsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        final Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();

        if (room == null || room.getId() != this.packet.readInt())
            return;

        if (room.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || this.client.getHabbo().hasPermission(Permission.ACC_ANYROOMOWNER)) {
            room.getRights().forEach(new TIntProcedure() {
                @Override
                public boolean execute(int value) {
                    Habbo habbo = room.getHabbo(value);

                    if (habbo != null) {
                        room.sendComposer(new NoSuchFlatComposer(room, value).compose());
                        habbo.getRoomUnit().removeStatus(RoomUnitStatus.FLAT_CONTROL);
                        habbo.getClient().sendResponse(new YouAreControllerComposer(habbo.getClient().getRevision(), room.getId(), RoomRightLevels.NONE));
                    }

                    return true;
                }
            });

            room.removeAllRights();
        }
    }
}
