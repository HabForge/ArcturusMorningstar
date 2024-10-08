package com.eu.habbo.messages.outgoing.recycler;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.items.Item;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import gnu.trove.set.hash.THashSet;

import java.util.Map;

public class RecyclerPrizesComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.RecyclerPrizes);
        this.response.appendInt(Emulator.getGameEnvironment().getCatalogManager().prizes.size());
        for (Map.Entry<Integer, THashSet<Item>> map : Emulator.getGameEnvironment().getCatalogManager().prizes.entrySet()) {
            this.response.appendInt(map.getKey());
            this.response.appendInt(Integer.valueOf(Emulator.getConfig().getValue("hotel.ecotron.rarity.chance." + map.getKey())));
            this.response.appendInt(map.getValue().size());
            for (Item item : map.getValue()) {
                this.response.appendString(item.getName());
                this.response.appendInt(1);
                this.response.appendString(item.getType().code.toLowerCase());
                this.response.appendInt(item.getSpriteId());
            }
        }
        return this.response;
    }
}
