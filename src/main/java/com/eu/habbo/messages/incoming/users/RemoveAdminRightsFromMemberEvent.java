package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.guilds.GuildMember;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.GuildMembershipUpdatedComposer;
import com.eu.habbo.messages.outgoing.users.HabboGroupDetailsComposer;
import com.eu.habbo.plugin.events.guilds.GuildRemovedAdminEvent;

public class RemoveAdminRightsFromMemberEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);

        if (guild != null) {
            if (guild.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || this.client.getHabbo().hasPermission(Permission.ACC_GUILD_ADMIN)) {
                int userId = this.packet.readInt();

                Room room = Emulator.getGameEnvironment().getRoomManager().getRoom(guild.getRoomId());
                Habbo habbo = Emulator.getGameEnvironment().getHabboManager().getHabbo(userId);

                GuildRemovedAdminEvent removedAdminEvent = new GuildRemovedAdminEvent(guild, userId, habbo);
                Emulator.getPluginManager().fireEvent(removedAdminEvent);

                if (removedAdminEvent.isCancelled())
                    return;

                Emulator.getGameEnvironment().getGuildManager().removeAdmin(guild, userId);

                if (habbo != null) {
                    habbo.getClient().sendResponse(new HabboGroupDetailsComposer(guild, this.client, false, Emulator.getGameEnvironment().getGuildManager().getGuildMember(guild.getId(), userId)));

                    if (room != null && habbo.getHabboInfo().getCurrentRoom() != null && habbo.getHabboInfo().getCurrentRoom() == room) room.refreshRightsForHabbo(habbo);
                }
                GuildMember guildMember = Emulator.getGameEnvironment().getGuildManager().getGuildMember(guildId, userId);

                this.client.sendResponse(new GuildMembershipUpdatedComposer(guild, guildMember));
            }
        }
    }
}
