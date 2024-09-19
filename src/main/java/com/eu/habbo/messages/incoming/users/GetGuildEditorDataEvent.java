package com.eu.habbo.messages.incoming.users;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.guilds.GuildPartsComposer;

public class GetGuildEditorDataEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new GuildPartsComposer());
    }
}
