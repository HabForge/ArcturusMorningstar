package com.eu.habbo.messages.incoming.moderator;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.incoming.MessageHandler;

public class ModKickEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        Emulator.getGameEnvironment().getModToolManager().kick(this.client.getHabbo(), Emulator.getGameEnvironment().getHabboManager().getHabbo(this.packet.readInt()), this.packet.readString());
    }
}
