package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.help.CallForHelpPendingCallsComposer;

public class GetPendingCallsForHelpEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        this.client.sendResponse(new CallForHelpPendingCallsComposer(Emulator.getGameEnvironment().getModToolManager().openTicketsForHabbo(this.client.getHabbo())));
    }
}
