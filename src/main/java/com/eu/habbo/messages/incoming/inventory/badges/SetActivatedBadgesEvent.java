package com.eu.habbo.messages.incoming.inventory.badges;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.HabboBadge;
import com.eu.habbo.habbohotel.users.inventory.BadgesComponent;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.HabboUserBadgesComposer;

import java.util.ArrayList;

public class SetActivatedBadgesEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        BadgesComponent.resetSlots(this.client.getHabbo());

        ArrayList<HabboBadge> updatedBadges = new ArrayList<>();
        ArrayList<Integer> usedSlots = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int slot = this.packet.readInt();
            if (slot < 1 || slot > 5)
                return;

            String badgeId = this.packet.readString();

            if (badgeId.length() == 0)
                continue;

            HabboBadge badge = this.client.getHabbo().getInventory().getBadgesComponent().getBadge(badgeId);
            if (badge != null && !updatedBadges.contains(badge) && !usedSlots.contains(slot)) {
                usedSlots.add(slot);
                badge.setSlot(slot);
                badge.needsUpdate(true);
                Emulator.getThreading().run(badge);
                updatedBadges.add(badge);
            }
        }

        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null) {
            this.client.getHabbo().getHabboInfo().getCurrentRoom().sendComposer(new HabboUserBadgesComposer(updatedBadges, this.client.getHabbo().getHabboInfo().getId()).compose());
        } else {
            this.client.sendResponse(new HabboUserBadgesComposer(updatedBadges, this.client.getHabbo().getHabboInfo().getId()));
        }
    }
}
