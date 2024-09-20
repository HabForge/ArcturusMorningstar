package com.eu.habbo.messages.rcon;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.outgoing.catalog.BuildersClubFurniCountComposer;
import com.eu.habbo.messages.outgoing.catalog.BundleDiscountRulesetComposer;
import com.eu.habbo.messages.outgoing.catalog.CatalogPublishedComposer;
import com.eu.habbo.messages.outgoing.catalog.GiftWrappingConfigurationComposer;
import com.eu.habbo.messages.outgoing.marketplace.MarketplaceConfigurationComposer;
import com.eu.habbo.messages.outgoing.recycler.RecyclerPrizesComposer;
import com.google.gson.Gson;

public class UpdateCatalog extends RCONMessage<UpdateCatalog.JSONUpdateCatalog> {

    public UpdateCatalog() {
        super(JSONUpdateCatalog.class);
    }

    @Override
    public void handle(Gson gson, JSONUpdateCatalog json) {
        Emulator.getGameEnvironment().getCatalogManager().initialize();
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new CatalogPublishedComposer());
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new BuildersClubFurniCountComposer(0));
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new BundleDiscountRulesetComposer());
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new MarketplaceConfigurationComposer());
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new GiftWrappingConfigurationComposer());
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new RecyclerPrizesComposer());
        Emulator.getGameEnvironment().getCraftingManager().reload();
    }

    static class JSONUpdateCatalog {
    }
}