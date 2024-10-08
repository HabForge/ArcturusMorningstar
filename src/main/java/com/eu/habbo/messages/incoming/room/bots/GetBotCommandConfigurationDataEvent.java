package com.eu.habbo.messages.incoming.room.bots;

import com.eu.habbo.habbohotel.bots.Bot;
import com.eu.habbo.habbohotel.permissions.Permission;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.room.bots.BotCommandConfigurationComposer;

public class GetBotCommandConfigurationDataEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Room room = this.client.getHabbo().getHabboInfo().getCurrentRoom();

        if (room == null)
            return;

        if (room.getOwnerId() == this.client.getHabbo().getHabboInfo().getId() || this.client.getHabbo().hasPermission(Permission.ACC_ANYROOMOWNER)) {
            int botId = this.packet.readInt();

            Bot bot = room.getBot(Math.abs(botId));

            if (bot == null)
                return;

            this.client.sendResponse(new BotCommandConfigurationComposer(bot, this.packet.readInt()));
        }
    }
}
