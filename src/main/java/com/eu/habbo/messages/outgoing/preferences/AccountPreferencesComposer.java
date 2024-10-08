package com.eu.habbo.messages.outgoing.preferences;

import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class AccountPreferencesComposer extends MessageComposer {
    private final Habbo habbo;

    public AccountPreferencesComposer(Habbo habbo) {
        this.habbo = habbo;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AccountPreferences);
        this.response.appendInt(this.habbo.getHabboStats().volumeSystem);
        this.response.appendInt(this.habbo.getHabboStats().volumeFurni);
        this.response.appendInt(this.habbo.getHabboStats().volumeTrax);
        this.response.appendBoolean(this.habbo.getHabboStats().preferOldChat);
        this.response.appendBoolean(this.habbo.getHabboStats().blockRoomInvites);
        this.response.appendBoolean(this.habbo.getHabboStats().blockCameraFollow);
        this.response.appendInt(this.habbo.getHabboStats().uiFlags);
        this.response.appendInt(this.habbo.getHabboStats().chatColor.getType());
        this.response.appendBoolean(false); // TODO(HabForge): wiredMenuButton
        this.response.appendBoolean(false); // TODO(HabForge): wiredInspectButton
        this.response.appendBoolean(false); // TODO(HabForge): playTestMode
        this.response.appendInt(0); // TODO(HabForge): variableSyntaxMode
        return this.response;
    }
}
