package com.eu.habbo.messages.incoming.handshake;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.handshake.UserObjectComposer;
import com.eu.habbo.messages.outgoing.perk.PerkAllowancesComposer;
import com.eu.habbo.messages.outgoing.preferences.AccountPreferencesComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class InfoRetrieveEvent extends MessageHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoRetrieveEvent.class);

    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo() != null) {
            //this.client.sendResponse(new TestComposer());

            //this.client.sendResponse(new UserObjectComposer(this.client.getHabbo()));
            //this.client.sendResponse(new CloseConnectionComposer());
            //this.client.sendResponse(new NavigatorSettingsComposer());
            //this.client.sendResponse(new UserRightsComposer(this.client.getHabbo()));

            //this.client.sendResponse(new CreditBalanceComposer(this.client.getHabbo()));
            //this.client.sendResponse(new ActivityPointsComposer(this.client.getHabbo()));
            //this.client.sendResponse(new FavouritesComposer());

            //this.client.sendResponse(new AchievementsScoreComposer(this.client.getHabbo()));
            //this.client.sendResponse(new FigureSetIdsComposer());
            //this.client.sendResponse(new HabboBroadcastComposer(Emulator.getTexts().getValue("hotel.alert.message.welcome").replace("%user%", this.client.getHabbo().getHabboInfo().getUsername()), this.client.getHabbo()));


            //

            ArrayList<ServerMessage> messages = new ArrayList<>();


            messages.add(new UserObjectComposer(this.client.getHabbo()).compose());
            messages.add(new PerkAllowancesComposer(this.client.getHabbo()).compose());

            messages.add(new AccountPreferencesComposer(this.client.getHabbo()).compose());


//
//

//
//
//


            this.client.sendResponses(messages);


        } else {
            LOGGER.debug("Attempted to request user data where Habbo was null.");
            Emulator.getGameServer().getGameClientManager().disposeClient(this.client);
        }
    }
}
