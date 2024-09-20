package com.eu.habbo.habbohotel.commands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.RoomChatMessageBubbles;
import com.eu.habbo.messages.outgoing.catalog.BuildersClubFurniCountComposer;
import com.eu.habbo.messages.outgoing.catalog.BundleDiscountRulesetComposer;
import com.eu.habbo.messages.outgoing.catalog.CatalogPublishedComposer;
import com.eu.habbo.messages.outgoing.catalog.GiftWrappingConfigurationComposer;
import com.eu.habbo.messages.outgoing.marketplace.MarketplaceConfigurationComposer;
import com.eu.habbo.messages.outgoing.recycler.RecyclerPrizesComposer;

public class UpdateCatalogCommand extends Command {

    public UpdateCatalogCommand() {
        super("cmd_update_catalogue", Emulator.getTexts().getValue("commands.keys.cmd_update_catalogue").split(";"));
    }

    @Override
    public boolean handle(GameClient gameClient, String[] params) {
        Emulator.getGameEnvironment().getCatalogManager().initialize();
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new CatalogPublishedComposer());
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new BuildersClubFurniCountComposer(0));
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new BundleDiscountRulesetComposer());
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new MarketplaceConfigurationComposer());
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new GiftWrappingConfigurationComposer());
        Emulator.getGameServer().getGameClientManager().sendBroadcastResponse(new RecyclerPrizesComposer());
        Emulator.getGameEnvironment().getCraftingManager().reload();
        gameClient.getHabbo().whisper(Emulator.getTexts().getValue("commands.succes.cmd_update_catalog"), RoomChatMessageBubbles.ALERT);
        return true;
    }
}
