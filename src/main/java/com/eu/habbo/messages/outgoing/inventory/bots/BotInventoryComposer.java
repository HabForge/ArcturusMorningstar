package com.eu.habbo.messages.outgoing.inventory.bots;

import com.eu.habbo.habbohotel.bots.Bot;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import gnu.trove.map.hash.THashMap;

public class BotInventoryComposer extends MessageComposer {
    private final Habbo habbo;

    public BotInventoryComposer(Habbo habbo) {
        this.habbo = habbo;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.BotInventory);

        THashMap<Integer, Bot> userBots = this.habbo.getInventory().getBotsComponent().getBots();
        this.response.appendInt(userBots.size());
        for (Bot bot : userBots.values()) {
            this.response.appendInt(bot.getId());
            this.response.appendString(bot.getName());
            this.response.appendString(bot.getMotto());
            this.response.appendString(bot.getGender().toString().toLowerCase().charAt(0) + "");
            this.response.appendString(bot.getFigure());
        }

        return this.response;
    }
}
