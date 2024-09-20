package com.eu.habbo.messages.incoming.competition;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.competition.SecondsUntilComposer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GetSecondsUntilEvent extends MessageHandler {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");

    @Override
    public void handle() throws Exception {
        String date = this.packet.readString();
        int secondsUntil = Math.max(0, (int) (dateFormat.parse(date).getTime() / 1000) - Emulator.getIntUnixTimestamp());

        this.client.sendResponse(new SecondsUntilComposer(date, secondsUntil));
    }
}
