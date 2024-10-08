package com.eu.habbo.messages.outgoing.friendlist;

import com.eu.habbo.habbohotel.messenger.MessengerBuddy;
import com.eu.habbo.habbohotel.messenger.MessengerCategory;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.HabboGender;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import gnu.trove.list.array.TIntArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FriendListUpdateComposer extends MessageComposer {
    private Collection<MessengerBuddy> buddies;

    private Habbo habbo;
    private int action;
    private TIntArrayList removedFriends;

    public FriendListUpdateComposer(Habbo habbo, MessengerBuddy buddy, Integer action) {
        this.habbo = habbo;
        this.buddies = Collections.singletonList(buddy);
        this.action = action;
    }

    public FriendListUpdateComposer(Habbo habbo, Collection<MessengerBuddy> buddies, Integer action) {
        this.habbo = habbo;
        this.buddies = buddies;
        this.action = action;
    }

    public FriendListUpdateComposer(Habbo habbo, TIntArrayList removedFriends) {
        this.habbo = habbo;
        this.removedFriends = removedFriends;
        this.action = -1;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FriendListUpdate);

        if (removedFriends != null) {
            this.response.appendInt(0);
            this.response.appendInt(removedFriends.size());

            for (int i = 0; i < removedFriends.size(); i++) {
                this.response.appendInt(-1);
                this.response.appendInt(removedFriends.get(i));
            }

            return this.response;
        }

        if (this.habbo != null && !this.habbo.getHabboInfo().getMessengerCategories().isEmpty()) {

            List<MessengerCategory> messengerCategories = this.habbo.getHabboInfo().getMessengerCategories();
            this.response.appendInt(messengerCategories.size());

            for (MessengerCategory mc : messengerCategories) {
                this.response.appendInt(mc.getId());
                this.response.appendString(mc.getName());
            }
        } else {
            this.response.appendInt(0);
        }

        this.response.appendInt(buddies.size()); // totalbuddies

        for (MessengerBuddy buddy : buddies) {
            this.response.appendInt(this.action); // -1 = removed friendId / 0 = updated friend / 1 = added friend
            this.response.appendInt(buddy.getId());
            if (this.action == -1) {
                continue;
            }
            this.response.appendString(buddy.getUsername());
            this.response.appendInt(buddy.getGender().equals(HabboGender.M) ? 0 : 1);
            this.response.appendBoolean(buddy.getOnline() == 1);
            this.response.appendBoolean(buddy.inRoom()); //In room
            this.response.appendString(buddy.getLook());
            this.response.appendInt(buddy.getCategoryId());
            this.response.appendString(buddy.getMotto());
            this.response.appendString(""); //Last seen as DATETIMESTRING
            this.response.appendString(""); //Realname or Facebookame as String
            this.response.appendBoolean(false); //Offline messaging.
            this.response.appendBoolean(false);
            this.response.appendBoolean(false);
            this.response.appendShort(buddy.getRelation());
        }

        return this.response;
    }
}
