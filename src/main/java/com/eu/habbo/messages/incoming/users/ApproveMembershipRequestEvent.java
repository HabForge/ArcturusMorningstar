package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.habbohotel.guilds.GuildMember;
import com.eu.habbo.habbohotel.guilds.GuildRank;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.GuildMemberMgmtFailedComposer;
import com.eu.habbo.messages.outgoing.users.GuildMembershipRejectedComposer;
import com.eu.habbo.messages.outgoing.users.HabboGroupDetailsComposer;
import com.eu.habbo.plugin.events.guilds.GuildAcceptedMembershipEvent;

public class ApproveMembershipRequestEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int guildId = this.packet.readInt();
        int userId = this.packet.readInt();

        Guild guild = Emulator.getGameEnvironment().getGuildManager().getGuild(guildId);
        Habbo habbo = Emulator.getGameEnvironment().getHabboManager().getHabbo(userId);

        if (guild != null) {
            GuildMember groupMember = Emulator.getGameEnvironment().getGuildManager().getGuildMember(guild, this.client.getHabbo());
            if (userId == this.client.getHabbo().getHabboInfo().getId() || guild.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || groupMember.getRank().equals(GuildRank.ADMIN) || groupMember.getRank().equals(GuildRank.OWNER) || this.client.getHabbo().hasPermission(Permission.ACC_GUILD_ADMIN)) {
                if (habbo != null) {
                    if (habbo.getHabboStats().hasGuild(guild.getId())) {
                        this.client.sendResponse(new GuildMemberMgmtFailedComposer(guild.getId(), GuildMemberMgmtFailedComposer.ALREADY_ACCEPTED));
                        return;
                    } else {
                        //Check the user has requested
                        GuildMember member = Emulator.getGameEnvironment().getGuildManager().getGuildMember(guild, habbo);
                        if (member == null || member.getRank().type != GuildRank.REQUESTED.type) {
                            this.client.sendResponse(new GuildMemberMgmtFailedComposer(guild.getId(), GuildMemberMgmtFailedComposer.NO_LONGER_MEMBER));
                            return;
                        } else {
                            GuildAcceptedMembershipEvent event = new GuildAcceptedMembershipEvent(guild, userId, habbo);
                            Emulator.getPluginManager().fireEvent(event);
                            if (!event.isCancelled()) {
                                habbo.getHabboStats().addGuild(guild.getId());
                                Emulator.getGameEnvironment().getGuildManager().joinGuild(guild, this.client, habbo.getHabboInfo().getId(), true);
                                guild.decreaseRequestCount();
                                guild.increaseMemberCount();
                                this.client.sendResponse(new GuildMembershipRejectedComposer(guild));
                                Room room = habbo.getHabboInfo().getCurrentRoom();
                                if (room != null) {
                                    if (room.getGuildId() == guildId) {
                                        habbo.getClient().sendResponse(new HabboGroupDetailsComposer(guild, habbo.getClient(), false, Emulator.getGameEnvironment().getGuildManager().getGuildMember(guildId, userId)));
                                        room.refreshRightsForHabbo(habbo);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Emulator.getGameEnvironment().getGuildManager().joinGuild(guild, this.client, userId, true);
                }
            }
        }
    }
}
