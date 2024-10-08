package com.eu.habbo.messages.incoming.preferences;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.plugin.events.users.UserSavedSettingsEvent;

public class SetIgnoreRoomInvitesEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.getHabbo().getHabboStats().blockRoomInvites = this.packet.readBoolean();
        Emulator.getPluginManager().fireEvent(new UserSavedSettingsEvent(this.client.getHabbo()));
    }
}
