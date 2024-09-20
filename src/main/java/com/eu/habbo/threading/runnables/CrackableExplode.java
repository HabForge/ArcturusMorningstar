package com.eu.habbo.threading.runnables;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.items.FurnitureType;
import com.eu.habbo.habbohotel.items.Item;
import com.eu.habbo.habbohotel.items.interactions.InteractionCrackable;
import com.eu.habbo.habbohotel.notifications.BubbleAlertKeys;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.outgoing.inventory.furni.FurniListInvalidateComposer;
import com.eu.habbo.messages.outgoing.notifications.NotificationDialogComposer;
import com.eu.habbo.messages.outgoing.notifications.UnseenItemsComposer;
import com.eu.habbo.messages.outgoing.room.engine.ObjectAddComposer;
import com.eu.habbo.messages.outgoing.room.engine.ObjectRemoveComposer;
import gnu.trove.map.hash.THashMap;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CrackableExplode implements Runnable {
    private final Room room;
    private final InteractionCrackable habboItem;
    private final Habbo habbo;
    private final boolean toInventory;
    private final short x;
    private final short y;

    public CrackableExplode(Room room, InteractionCrackable item, Habbo habbo, boolean toInventory, short x, short y) {
        this.room = room;
        this.habboItem = item;
        this.habbo = habbo;
        this.toInventory = toInventory;

        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        if (this.habboItem.getRoomId() == 0) {
            return;
        }

        if (!this.habboItem.resetable()) {
            this.habboItem.setExtradata("2");
            this.room.updateItem(habboItem);
            Emulator.getThreading().run(new QueryDeleteHabboItem(this.habboItem.getId()));
            Emulator.getThreading().run(new RemoveFloorItemTask(this.room, this.habboItem),2000);
            Executors.newSingleThreadScheduledExecutor().schedule(this::handleRewards, 2, TimeUnit.SECONDS);
        } else {
            this.habboItem.reset(this.room);
        }
    }

    private void handleRewards() {
        List<Integer> items = Emulator.getGameEnvironment().getItemManager().getCrackableReward(this.habboItem.getBaseItem().getId());

        if (items != null && !items.isEmpty()) {
            if (items.size() > 1) {
                handleMultipleItems(items);
            } else {
                handleSingleItem(items.get(0));
            }
        }

        this.room.updateTile(this.room.getLayout().getTile(this.x, this.y));

    }

    private void handleMultipleItems(List<Integer> items) {
        for (Integer itemId : items) {
            Item rewardItem = Emulator.getGameEnvironment().getItemManager().getItem(itemId);
            if (rewardItem == null) {
                return;
            }
            HabboItem newItem = createNewItem(rewardItem);
            if (newItem != null) {
                addToInventory(newItem);
            }
        }
        sendNotification();
    }

    private void handleSingleItem(int itemId) {
        Item rewardItem = Emulator.getGameEnvironment().getItemManager().getItem(itemId);
        if (rewardItem == null) {
            return;
        }
        HabboItem newItem = createNewItem(rewardItem);
        if (newItem != null) {
            if (this.toInventory || newItem.getBaseItem().getType() == FurnitureType.WALL) {
                addToInventory(newItem);
            } else {
                placeItemInRoom(newItem);
            }
        }
    }

    private HabboItem createNewItem(Item rewardItem) {
        return Emulator.getGameEnvironment()
                .getItemManager()
                .createItem(
                        this.habboItem.allowAnyone() ? this.habbo.getHabboInfo().getId() : this.habboItem.getUserId(),
                        rewardItem,
                        0,
                        0,
                        ""
                );
    }

    private void addToInventory(HabboItem newItem) {
        this.habbo.getInventory().getItemsComponent().addItem(newItem);
        this.habbo.getClient().sendResponse(new UnseenItemsComposer(newItem));
        this.habbo.getClient().sendResponse(new FurniListInvalidateComposer());
    }

    private void placeItemInRoom(HabboItem newItem) {
        newItem.setX(this.x);
        newItem.setY(this.y);
        newItem.setZ(this.room.getStackHeight(this.x, this.y, false));

        newItem.setRoomId(this.room.getId());
        newItem.needsUpdate(true);
        this.room.addHabboItem(newItem);
        this.room.updateItem(newItem);
        this.room.sendComposer(
                new ObjectAddComposer(
                        newItem, this.room.getFurniOwnerNames().get(newItem.getUserId()))
                        .compose()
        );
    }

    private void sendNotification() {
        THashMap<String, String> keys = new THashMap<>();
        keys.put("display", "BUBBLE");
        keys.put("message", Emulator.getTexts().getValue("crackables.reward_received.message"));
        this.habbo.getClient().sendResponse(new NotificationDialogComposer(BubbleAlertKeys.REWARD_RECEIVED.key, keys));
    }

}
