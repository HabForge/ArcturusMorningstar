package com.eu.habbo.messages.outgoing.notifications;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityPointsComposer extends MessageComposer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityPointsComposer.class);

    private final Habbo habbo;

    public ActivityPointsComposer(Habbo habbo) {
        this.habbo = habbo;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.ActivityPoints);
        String[] pointsTypes = Emulator.getConfig().getValue("seasonal.types").split(";");
        this.response.appendInt(pointsTypes.length);
        for (String s : pointsTypes) {
            int type;
            try {
                type = Integer.valueOf(s);
            } catch (Exception e) {
                LOGGER.error("Caught exception", e);
                return null;
            }

            this.response.appendInt(type);
            this.response.appendInt(this.habbo.getHabboInfo().getCurrencyAmount(type));
        }
        return this.response;
    }
}
