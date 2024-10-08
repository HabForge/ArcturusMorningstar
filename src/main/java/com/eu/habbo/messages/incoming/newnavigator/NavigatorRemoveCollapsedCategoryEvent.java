package com.eu.habbo.messages.incoming.newnavigator;

import com.eu.habbo.habbohotel.navigation.DisplayMode;
import com.eu.habbo.messages.incoming.MessageHandler;

public class NavigatorRemoveCollapsedCategoryEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        String category = this.packet.readString();
        this.client.getHabbo().getHabboStats().navigatorWindowSettings.setDisplayMode(category, DisplayMode.VISIBLE);
    }
}
