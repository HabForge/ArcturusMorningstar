package com.eu.habbo.messages.incoming.help;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.guides.GuardianTicket;
import com.eu.habbo.habbohotel.modtool.ModToolChatLog;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.help.CallForHelpDisabledNotifyComposer;
import com.eu.habbo.messages.outgoing.help.GuideTicketCreationResultComposer;

import java.util.ArrayList;

public class ChatReviewSessionCreateEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().getHabboStats().allowTalk()) {
            this.client.sendResponse(new CallForHelpDisabledNotifyComposer());
            return;
        }

        int userId = this.packet.readInt();
        int roomId = this.packet.readInt();

        if (userId == this.client.getHabbo().getHabboInfo().getId()) {
            return;
        }

        Room room = Emulator.getGameEnvironment().getRoomManager().getRoom(roomId);

        if (room != null) {
            Habbo habbo = room.getHabbo(userId);

            if (habbo != null) {
                GuardianTicket ticket = Emulator.getGameEnvironment().getGuideManager().getOpenReportedHabboTicket(habbo);

                if (ticket != null) {
                    this.client.sendResponse(new GuideTicketCreationResultComposer(GuideTicketCreationResultComposer.ALREADY_REPORTED));
                    return;
                }

                ArrayList<ModToolChatLog> chatLog = Emulator.getGameEnvironment().getModToolManager().getRoomChatlog(roomId);

                if (chatLog.isEmpty()) {
                    this.client.sendResponse(new GuideTicketCreationResultComposer(GuideTicketCreationResultComposer.NO_CHAT));
                    return;
                }

                Emulator.getGameEnvironment().getGuideManager().addGuardianTicket(new GuardianTicket(this.client.getHabbo(), habbo, chatLog));

                this.client.sendResponse(new GuideTicketCreationResultComposer(GuideTicketCreationResultComposer.RECEIVED));
            }
        }
    }
}
