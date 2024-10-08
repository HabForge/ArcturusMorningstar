package com.eu.habbo.messages.rcon;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.moderation.ModeratorComposer;
import com.eu.habbo.messages.outgoing.notifications.HabboBroadcastComposer;
import com.google.gson.Gson;

import java.util.Map;

public class HotelAlert extends RCONMessage<HotelAlert.JSONHotelAlert> {

    public HotelAlert() {
        super(JSONHotelAlert.class);
    }

    @Override
    public void handle(Gson gson, JSONHotelAlert object) {
        ServerMessage serverMessage;
        if (object.url.isEmpty()) {
            serverMessage = new HabboBroadcastComposer(object.message).compose();
        } else {
            serverMessage = new ModeratorComposer(object.message, object.url).compose();
        }

        if (serverMessage != null) {
            for (Map.Entry<Integer, Habbo> set : Emulator.getGameEnvironment().getHabboManager().getOnlineHabbos().entrySet()) {
                Habbo habbo = set.getValue();
                if (habbo.getHabboStats().blockStaffAlerts)
                    continue;

                habbo.getClient().sendResponse(serverMessage);
            }
        }
    }

    static class JSONHotelAlert {

        public String message;


        public String url = "";
    }
}
