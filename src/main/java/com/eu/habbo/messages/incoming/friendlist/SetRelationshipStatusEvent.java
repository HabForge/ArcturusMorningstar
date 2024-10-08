package com.eu.habbo.messages.incoming.friendlist;

import com.eu.habbo.habbohotel.messenger.MessengerBuddy;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.friendlist.FriendListUpdateComposer;
import com.eu.habbo.plugin.events.users.friends.UserRelationShipEvent;

public class SetRelationshipStatusEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int userId = this.packet.readInt();
        int relationId = this.packet.readInt();

        MessengerBuddy buddy = this.client.getHabbo().getMessenger().getFriends().get(userId);
        if (buddy != null && relationId >= 0 && relationId <= 3) {
            UserRelationShipEvent event = new UserRelationShipEvent(this.client.getHabbo(), buddy, relationId);
            if (!event.isCancelled()) {
                buddy.setRelation(event.relationShip);
                this.client.sendResponse(new FriendListUpdateComposer(this.client.getHabbo(), buddy, 0));
            }
        }
    }
}
