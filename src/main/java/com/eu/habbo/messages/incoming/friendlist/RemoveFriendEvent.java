package com.eu.habbo.messages.incoming.friendlist;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.messenger.Messenger;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.friendlist.FriendListUpdateComposer;
import gnu.trove.list.array.TIntArrayList;

public class RemoveFriendEvent extends MessageHandler {

    private final TIntArrayList removedFriends;

    public RemoveFriendEvent() {
        this.removedFriends = new TIntArrayList();
    }

    @Override
    public void handle() throws Exception {
        int count = this.packet.readInt();
        for (int i = 0; i < count; i++) {
            int habboId = this.packet.readInt();
            this.removedFriends.add(habboId);

            Messenger.unfriend(this.client.getHabbo().getHabboInfo().getId(), habboId);
            this.client.getHabbo().getMessenger().removeBuddy(habboId);

            Habbo habbo = Emulator.getGameServer().getGameClientManager().getHabbo(habboId);

            if (habbo != null) {
                habbo.getMessenger().removeBuddy(this.client.getHabbo());
                habbo.getClient().sendResponse(new FriendListUpdateComposer(this.client.getHabbo(), new TIntArrayList(new int[]{this.client.getHabbo().getHabboInfo().getId()})));
            }
        }

        this.client.sendResponse(new FriendListUpdateComposer(this.client.getHabbo(), this.removedFriends));
    }
}
