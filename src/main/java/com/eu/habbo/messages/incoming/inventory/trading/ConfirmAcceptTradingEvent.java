package com.eu.habbo.messages.incoming.inventory.trading;

import com.eu.habbo.habbohotel.rooms.RoomTrade;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;

public class ConfirmAcceptTradingEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Habbo habbo = this.client.getHabbo();
        RoomTrade trade = habbo.getHabboInfo().getCurrentRoom().getActiveTradeForHabbo(habbo);

        if (trade == null || !trade.getRoomTradeUserForHabbo(habbo).getAccepted())
            return;

        trade.confirm(habbo);
    }
}
