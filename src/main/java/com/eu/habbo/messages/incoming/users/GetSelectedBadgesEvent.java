package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.inventory.BadgesComponent;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.HabboUserBadgesComposer;

public class GetSelectedBadgesEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int userId = this.packet.readInt();
        Habbo habbo = Emulator.getGameServer().getGameClientManager().getHabbo(userId);

        if (habbo == null || habbo.getHabboInfo() == null || habbo.getInventory() == null || habbo.getInventory().getBadgesComponent() == null)
            this.client.sendResponse(new HabboUserBadgesComposer(BadgesComponent.getBadgesOfflineHabbo(userId), userId));
        else
            this.client.sendResponse(new HabboUserBadgesComposer(habbo.getInventory().getBadgesComponent().getWearingBadges(), habbo.getHabboInfo().getId()));
    }
}
