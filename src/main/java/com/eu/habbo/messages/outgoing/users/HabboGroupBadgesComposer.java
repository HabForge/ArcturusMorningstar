package com.eu.habbo.messages.outgoing.users;

import com.eu.habbo.habbohotel.guilds.Guild;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import gnu.trove.map.hash.THashMap;
import gnu.trove.procedure.TObjectObjectProcedure;

public class HabboGroupBadgesComposer extends MessageComposer {
    private final THashMap<Integer, String> guildBadges;

    public HabboGroupBadgesComposer(Guild guild) {
        this.guildBadges = new THashMap<>();
        this.guildBadges.put(guild.getId(), guild.getBadge());
    }

    public HabboGroupBadgesComposer(THashMap<Integer, String> guildBadges) {
        this.guildBadges = guildBadges;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.HabboGroupBadges);
        this.response.appendInt(this.guildBadges.size());

        this.guildBadges.forEachEntry(new TObjectObjectProcedure<Integer, String>() {
            @Override
            public boolean execute(Integer guildId, String badge) {
                HabboGroupBadgesComposer.this.response.appendInt(guildId);
                HabboGroupBadgesComposer.this.response.appendString(badge);
                return true;
            }
        });
        return this.response;
    }
}