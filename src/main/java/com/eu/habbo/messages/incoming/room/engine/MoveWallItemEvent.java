package com.eu.habbo.messages.incoming.room.engine;

import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.rooms.FurnitureMovementError;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.rooms.RoomRightLevels;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.habbohotel.notifications.BubbleAlertKeys;
import com.eu.habbo.messages.outgoing.notifications.NotificationDialogComposer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveWallItemEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();

        if (room == null)
            return;

        if (!room.hasRights(this.client.getHabbo()) && !this.client.getHabbo().hasPermission(Permission.ACC_PLACEFURNI) && !(room.getGuildId() > 0 && room.getGuildRightLevel(this.client.getHabbo()).isEqualOrGreaterThan(RoomRightLevels.GUILD_RIGHTS))) {
            this.client.sendResponse(new NotificationDialogComposer(BubbleAlertKeys.FURNITURE_PLACEMENT_ERROR.key, FurnitureMovementError.NO_RIGHTS.errorCode));
            return;
        }

        int itemId = this.packet.readInt();
        String wallPosition = this.packet.readString();

        if (itemId <= 0 || wallPosition.length() <= 13)
            return;

        HabboItem item = room.getHabboItem(itemId);

        if (item == null)
            return;

        Pattern wallPostitonPattern = Pattern.compile(":w=(\\d+),(\\d+) l=(\\d+),(\\d+) (l|r)");
        Matcher wallPositionString = wallPostitonPattern.matcher(wallPosition);

        if (wallPositionString.find()) {
            item.setX((short) Integer.parseInt(wallPositionString.group(1)));
            item.setY((short) Integer.parseInt(wallPositionString.group(2)));
            item.setZ(Integer.parseInt(wallPositionString.group(4)));
            item.setRotation(wallPositionString.group(5).equals("l") ? 0 : 1);
            item.setWallItemOffset((short)Integer.parseInt(wallPositionString.group(3)));
        }

        item.needsUpdate(true);
        room.updateItem(item);
    }
}
