package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guides.GuardianTicket;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.help.GuideReportingStatusComposer;

import java.util.Calendar;

public class GetGuideReportingStatusEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        GuardianTicket ticket = Emulator.getGameEnvironment().getGuideManager().getRecentTicket(this.client.getHabbo());

        if (ticket != null) {
            if (ticket.inProgress()) {
                this.client.sendResponse(new GuideReportingStatusComposer(GuideReportingStatusComposer.ONGOING_HELPER_CASE, 1));
                return;
            }

            if ((Calendar.getInstance().getTime().getTime() / 1000) - ticket.getDate().getTime() < Emulator.getConfig().getInt("guardians.reporting.cooldown")) {
                this.client.sendResponse(new GuideReportingStatusComposer(GuideReportingStatusComposer.TOO_RECENT, 1));
                return;
            }
        }

        this.client.sendResponse(new GuideReportingStatusComposer(GuideReportingStatusComposer.START_REPORT, 0));
    }
}
