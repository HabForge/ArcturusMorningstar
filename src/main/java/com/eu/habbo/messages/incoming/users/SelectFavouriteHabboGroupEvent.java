package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.engine.FavoriteMembershipUpdateComposer;
import com.eu.habbo.messages.outgoing.users.ExtendedProfileComposer;
import com.eu.habbo.messages.outgoing.users.HabboGroupBadgesComposer;
import com.eu.habbo.plugin.events.guilds.GuildFavoriteSetEvent;

public class SelectFavouriteHabboGroupEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);

        if (this.client.getHabbo().getHabboStats().hasGuild(guildId)) {
            GuildFavoriteSetEvent favoriteSetEvent = new GuildFavoriteSetEvent(guild, this.client.getHabbo());
            Emulator.getPluginManager().fireEvent(favoriteSetEvent);

            if (favoriteSetEvent.isCancelled())
                return;

            this.client.getHabbo().getHabboStats().guild = guildId;

            if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null) {
                if (guild != null) {
                    this.client.getHabbo().getHabboInfo().getCurrentRoom().sendComposer(new HabboGroupBadgesComposer(guild).compose());
                    this.client.getHabbo().getHabboInfo().getCurrentRoom().sendComposer(new FavoriteMembershipUpdateComposer(this.client.getHabbo().getRoomUnit(), guild).compose());
                }
            }

            this.client.sendResponse(new ExtendedProfileComposer(this.client.getHabbo(), this.client));
        }
    }
}
