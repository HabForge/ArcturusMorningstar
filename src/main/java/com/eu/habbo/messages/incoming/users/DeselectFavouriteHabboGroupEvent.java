package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.engine.FavoriteMembershipUpdateComposer;
import com.eu.habbo.messages.outgoing.users.ExtendedProfileComposer;
import com.eu.habbo.plugin.events.guilds.GuildRemovedFavoriteEvent;

public class DeselectFavouriteHabboGroupEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();

        if (this.client.getHabbo().getHabboStats().hasGuild(guildId)) {
            Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);
            GuildRemovedFavoriteEvent favoriteEvent = new GuildRemovedFavoriteEvent(guild, this.client.getHabbo());
            Emulator.getPluginManager().fireEvent(favoriteEvent);
            if (favoriteEvent.isCancelled())
                return;

            this.client.getHabbo().getHabboStats().guild = 0;

            if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null && guild != null) {
                this.client.getHabbo().getHabboInfo().getCurrentRoom().sendComposer(new FavoriteMembershipUpdateComposer(this.client.getHabbo().getRoomUnit(), null).compose());
            }

            this.client.sendResponse(new ExtendedProfileComposer(this.client.getHabbo(), this.client));
        }
    }
}
