package com.eu.habbo.messages.incoming.catalog;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.catalog.LimitedOfferAppearingNextComposer;

public class GetLimitedOfferAppearingNextEvent extends MessageHandler {
    public static boolean ENABLED = false;
    public static int TIMESTAMP;
    public static int ITEM_ID;
    public static int PAGE_ID;
    public static String ITEM_NAME;

    @Override
    public void handle() throws Exception {
        if (ENABLED) {
            int timeremaining = Math.max(TIMESTAMP - Emulator.getIntUnixTimestamp(), -1);
            this.client.sendResponse(new LimitedOfferAppearingNextComposer(
                    timeremaining,
                    timeremaining > 0 ? -1 : ITEM_ID,
                    timeremaining > 0 ? -1 : PAGE_ID,
                    timeremaining > 0 ? "" : ITEM_NAME));
        }
    }
}
