package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.guilds.GuildMember;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.navigator.GetGuestRoomResultComposer;
import com.eu.habbo.messages.outgoing.room.engine.FavoriteMembershipUpdateComposer;
import com.eu.habbo.messages.outgoing.users.HabboGroupDeactivatedComposer;
import com.eu.habbo.plugin.events.guilds.GuildDeletedEvent;
import gnu.trove.set.hash.THashSet;

public class DeactivateGuildEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);

        if (guild != null) {
            if (guild.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || this.client.getHabbo().hasPermission(Permission.ACC_GUILD_ADMIN)) //TODO Add staff permission override.
            {
                THashSet<GuildMember> members = Emulator.getGameEnvironment().getGuildManager().getGuildMembers(guild.getId());

                for (GuildMember member : members) {
                    Habbo habbo = Emulator.getGameServer().getGameClientManager().getHabbo(member.getUserId());
                    if (habbo != null)
                        if (habbo.getHabboInfo().getCurrentRoom() != null && habbo.getRoomUnit() != null)
                            habbo.getHabboInfo().getCurrentRoom().sendComposer(new FavoriteMembershipUpdateComposer(habbo.getRoomUnit(), null).compose());
                }

                Emulator.getGameEnvironment().getGuildManager().deleteGuild(guild);
                Emulator.getPluginManager().fireEvent(new GuildDeletedEvent(guild, this.client.getHabbo()));
                Emulator.getGameEnvironment().getRoomManager().getRoom(guild.getRoomId()).sendComposer(new HabboGroupDeactivatedComposer(guildId).compose());

                if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null) {
                    if (guild.getRoomId() == this.client.getHabbo().getHabboInfo().getCurrentRoom().getId()) {
                        this.client.sendResponse(new GetGuestRoomResultComposer(this.client.getHabbo().getHabboInfo().getCurrentRoom(), this.client.getHabbo(), false, false));
                    }
                }
            }
        }
    }
}
