package com.eu.habbo.messages.incoming.preferences;

import com.eu.habbo.messages.incoming.MessageHandler;

public class SetUIFlagsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int flags = this.packet.readInt();

        this.client.getHabbo().getHabboStats().uiFlags = flags;
        this.client.getHabbo().getHabboStats().run();
    }
}
