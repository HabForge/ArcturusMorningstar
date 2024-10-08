package com.eu.habbo.messages.incoming.friendfurni;

import com.eu.habbo.habbohotel.items.interactions.InteractionLoveLock;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.HabboItem;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.friendfurni.FriendFurniCancelLockComposer;
import com.eu.habbo.messages.outgoing.friendfurni.FriendFurniOtherLockConfirmedComposer;

public class FriendFurniConfirmLockEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int itemId = this.packet.readInt();

        if (this.packet.readBoolean()) {
            if (this.client.getHabbo().getHabboInfo().getCurrentRoom() == null)
                return;

            HabboItem item = this.client.getHabbo().getHabboInfo().getCurrentRoom().getHabboItem(itemId);

            if (item == null)
                return;

            if (item instanceof InteractionLoveLock) {
                int userId = 0;

                if (((InteractionLoveLock) item).userOneId == this.client.getHabbo().getHabboInfo().getId() && ((InteractionLoveLock) item).userTwoId != 0) {
                    userId = ((InteractionLoveLock) item).userTwoId;
                } else if (((InteractionLoveLock) item).userOneId != 0 && ((InteractionLoveLock) item).userTwoId == this.client.getHabbo().getHabboInfo().getId()) {
                    userId = ((InteractionLoveLock) item).userOneId;
                }

                if (userId > 0) {
                    Habbo habbo = this.client.getHabbo().getHabboInfo().getCurrentRoom().getHabbo(userId);

                    if (habbo != null) {
                        habbo.getClient().sendResponse(new FriendFurniOtherLockConfirmedComposer((InteractionLoveLock) item));

                        habbo.getClient().sendResponse(new FriendFurniCancelLockComposer((InteractionLoveLock) item));
                        this.client.sendResponse(new FriendFurniCancelLockComposer((InteractionLoveLock) item));

                        ((InteractionLoveLock) item).lock(habbo, this.client.getHabbo(), this.client.getHabbo().getHabboInfo().getCurrentRoom());
                    }
                }
            }
        }
    }
}
