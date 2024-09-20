package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.guilds.GuildMember;
import com.eu.habbo.habbohotel.guilds.GuildRank;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.engine.FavoriteMembershipUpdateComposer;
import com.eu.habbo.messages.outgoing.users.GuildMembershipRejectedComposer;
import com.eu.habbo.messages.outgoing.users.HabboGroupDetailsComposer;
import com.eu.habbo.plugin.events.guilds.GuildRemovedMemberEvent;

public class KickMemberEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();
        int userId = this.packet.readInt();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);


        if (guild != null) {
            GuildMember member = Emulator.getGameEnvironment().getGuildManager().getGuildMember(guild, this.client.getHabbo());
            if (userId == this.client.getHabbo().getHabboInfo().getId() || guild.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || member.getRank().equals(GuildRank.OWNER) || member.getRank().equals(GuildRank.ADMIN) || this.client.getHabbo().hasPermission(Permission.ACC_GUILD_ADMIN)) {
                Habbo habbo = Emulator.getGameEnvironment().getHabboManager().getHabbo(userId);
                GuildRemovedMemberEvent removedMemberEvent = new GuildRemovedMemberEvent(guild, userId, habbo);
                Emulator.getPluginManager().fireEvent(removedMemberEvent);
                if (removedMemberEvent.isCancelled())
                    return;

                Emulator.getGameEnvironment().getGuildManager().removeMember(guild, userId);
                guild.decreaseMemberCount();

                if (userId != this.client.getHabbo().getHabboInfo().getId()) {
                    this.client.sendResponse(new GuildMembershipRejectedComposer(guild));
                }

                Room room = Emulator.getGameEnvironment().getRoomManager().loadRoom(guild.getRoomId());

                if (habbo != null) {
                    habbo.getHabboStats().removeGuild(guild.getId());
                    if (habbo.getHabboStats().guild == guildId)
                        habbo.getHabboStats().guild = 0;

                    if (room != null) {
                        if (habbo.getHabboInfo().getCurrentRoom() != null && habbo.getRoomUnit() != null)
                            habbo.getHabboInfo().getCurrentRoom().sendComposer(new FavoriteMembershipUpdateComposer(habbo.getRoomUnit(), null).compose());
                        if (habbo.getHabboInfo().getCurrentRoom() == room)
                            room.refreshRightsForHabbo(habbo);
                    }

                    habbo.getClient().sendResponse(new HabboGroupDetailsComposer(guild, habbo.getClient(), false, null));
                }

                if (room != null) {
                    if (room.getGuildId() == guildId) {
                        room.ejectUserFurni(userId);
                    }
                }
            }
        }
    }
}
