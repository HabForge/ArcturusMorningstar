package com.eu.habbo.messages.incoming.inventory.furni;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.achievements.AchievementManager;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.inventory.furni.FurniListRemoveComposer;
import com.eu.habbo.messages.outgoing.room.engine.RoomPropertyComposer;

public class RequestRoomPropertySetEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() == null)
            return;

        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();

        if (room.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || room.hasRights(this.client.getHabbo()) || this.client.getHabbo().hasPermission(Permission.ACC_PLACEFURNI)) {
            int itemId = this.packet.readInt();
            HabboItem item = this.client.getHabbo().getInventory().getItemsComponent().getHabboItem(itemId);

            if (item == null) {
                this.client.sendResponse(new FurniListRemoveComposer(itemId));
                return;
            }

            if (item.getBaseItem().getName().equals("floor")) {
                room.setFloorPaint(item.getExtradata());

                AchievementManager.progressAchievement(this.client.getHabbo(), Emulator.getGameEnvironment().getAchievementManager().getAchievement("RoomDecoFloor"));
            } else if (item.getBaseItem().getName().equals("wallpaper")) {
                room.setWallPaint(item.getExtradata());

                AchievementManager.progressAchievement(this.client.getHabbo(), Emulator.getGameEnvironment().getAchievementManager().getAchievement("RoomDecoWallpaper"));
            } else if (item.getBaseItem().getName().equals("landscape")) {
                room.setBackgroundPaint(item.getExtradata());

                AchievementManager.progressAchievement(this.client.getHabbo(), Emulator.getGameEnvironment().getAchievementManager().getAchievement("RoomDecoLandscape"));
            } else
                return;

            this.client.getHabbo().getInventory().getItemsComponent().removeHabboItem(item);
            room.setNeedsUpdate(true);
            room.sendComposer(new RoomPropertyComposer(item.getBaseItem().getName(), item.getExtradata()).compose());
            item.needsDelete(true);
            Emulator.getThreading().run(item);
            this.client.sendResponse(new FurniListRemoveComposer(itemId));
        }
    }
}
