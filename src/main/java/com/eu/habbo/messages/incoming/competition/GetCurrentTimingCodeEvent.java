package com.eu.habbo.messages.incoming.competition;

import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.competition.CurrentTimingCodeComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCurrentTimingCodeEvent extends MessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetCurrentTimingCodeEvent.class);


    @Override
    public void handle() {


        try {
            String data = this.packet.readString();
            if (data.contains(";")) {
                String[] d = data.split(";");

                for (String s : d) {
                    if (s.contains(",")) {
                        this.client.sendResponse(new CurrentTimingCodeComposer(s, s.split(",")[s.split(",").length - 1]));
                    } else {
                        this.client.sendResponse(new CurrentTimingCodeComposer(data, s));
                    }

                    break;
                }

                //this.client.sendResponse(new CurrentTimingCodeComposer("2013-05-08 13:0", "gamesmaker"));
            } else {
                this.client.sendResponse(new CurrentTimingCodeComposer(data, data.split(",")[data.split(",").length - 1]));
            }
        } catch (Exception e) {
            LOGGER.error("Caught exception", e);
        }
    }
}
