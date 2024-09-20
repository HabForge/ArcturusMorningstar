package com.eu.habbo.messages.outgoing.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GuideOnDutyStatusComposer extends MessageComposer {
    private final boolean onDuty;

    public GuideOnDutyStatusComposer(boolean onDuty) {
        this.onDuty = onDuty;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GuideOnDutyStatus);
        this.response.appendBoolean(this.onDuty); //OnDuty
        this.response.appendInt(0); //Guides On Duty
        this.response.appendInt(Emulator.getGameEnvironment().getGuideManager().getGuidesCount()); //Helpers On Duty
        this.response.appendInt(Emulator.getGameEnvironment().getGuideManager().getGuardiansCount()); //Guardians On Duty
        return this.response;
    }
}
