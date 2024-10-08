package com.eu.habbo.messages.outgoing.perk;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class CitizenshipVipOfferPromoEnabledComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CitizenshipVipOfferPromoEnabled);
        //Empty Body
        return this.response;
    }
}