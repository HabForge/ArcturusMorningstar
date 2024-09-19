package com.eu.habbo.messages.incoming.talent;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.users.UserCitizinShipComposer;

public class GetTalentTrackLevelEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new UserCitizinShipComposer(this.packet.readString()));
    }
}
