package com.eu.habbo.messages.incoming.room.furniture;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.achievements.AchievementManager;
import com.eu.habbo.habbohotel.items.interactions.InteractionPostIt;
import com.eu.habbo.habbohotel.items.interactions.InteractionStickyPole;
import com.eu.habbo.habbohotel.rooms.FurnitureMovementError;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.habbohotel.notifications.BubbleAlertKeys;
import com.eu.habbo.messages.outgoing.inventory.furni.FurniListRemoveComposer;
import com.eu.habbo.messages.outgoing.notifications.NotificationDialogComposer;
import com.eu.habbo.messages.outgoing.room.engine.ItemAddComposer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlacePostItEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int itemId = this.packet.readInt();
        String location = this.packet.readString();
        String wallPosition = this.packet.readString();

        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();

        if (room != null) {
            if (room.hasRights(this.client.getHabbo()) || !room.getRoomSpecialTypes().getItemsOfType(InteractionStickyPole.class).isEmpty()) {
                HabboItem item = this.client.getHabbo().getInventory().getItemsComponent().getHabboItem(itemId);

                if (item instanceof InteractionPostIt) {
                    if (room.getPostItNotes().size() < Room.MAXIMUM_POSTITNOTES) {
                        room.addHabboItem(item);
                        item.setExtradata("FFFF33");
                        item.setRoomId(this.client.getHabbo().getHabboInfo().getCurrentRoom().getId());

                        Pattern wallPostitonPattern = Pattern.compile(":w=(\\d+),(\\d+) l=(\\d+),(\\d+) (l|r)");
                        Matcher wallPositionString = wallPostitonPattern.matcher(wallPosition);

                        if (wallPositionString.find()) {
                            item.setX((short) Integer.parseInt(wallPositionString.group(1)));
                            item.setY((short) Integer.parseInt(wallPositionString.group(2)));
                            item.setZ(Integer.parseInt(wallPositionString.group(4)));
                            item.setRotation(wallPositionString.group(5).equals("l") ? 0 : 1);
                            item.setWallItemOffset((short)Integer.parseInt(wallPositionString.group(3)));
                        }

                        item.setUserId(this.client.getHabbo().getHabboInfo().getId());
                        item.needsUpdate(true);
                        room.sendComposer(new ItemAddComposer(item, this.client.getHabbo().getHabboInfo().getUsername()).compose());
                        this.client.getHabbo().getInventory().getItemsComponent().removeHabboItem(item);
                        this.client.sendResponse(new FurniListRemoveComposer(item.getGiftAdjustedId()));
                        item.setFromGift(false);
                        Emulator.getThreading().run(item);

                        if (room.getOwnerId() != this.client.getHabbo().getHabboInfo().getId()) {
                            AchievementManager.progressAchievement(room.getOwnerId(), Emulator.getGameEnvironment().getAchievementManager().getAchievement("NotesReceived"));
                            AchievementManager.progressAchievement(this.client.getHabbo().getHabboInfo().getId(), Emulator.getGameEnvironment().getAchievementManager().getAchievement("NotesLeft"));
                        }

                    }
                    else {
                        this.client.sendResponse(new NotificationDialogComposer(BubbleAlertKeys.FURNITURE_PLACEMENT_ERROR.key, FurnitureMovementError.MAX_STICKIES.errorCode));
                    }

                    //this.client.sendResponse(new RequestSpamWallPostItComposer(item));
                }
            }
        }
    }
}
