package com.eu.habbo.messages.incoming.sound;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.preferences.AccountPreferencesComposer;

public class GetSoundSettingsEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new AccountPreferencesComposer(this.client.getHabbo()));
    }
}
