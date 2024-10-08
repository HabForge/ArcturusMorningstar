package com.eu.habbo.messages.incoming.room.action;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.navigator.FlatAccessDeniedComposer;
import com.eu.habbo.messages.outgoing.room.session.CloseConnectionComposer;
import com.eu.habbo.messages.outgoing.room.session.FlatAccessibleComposer;

public class LetUserInEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        if (this.client.getHabbo().getHabboInfo().getCurrentRoom() != null && this.client.getHabbo().getHabboInfo().getCurrentRoom().hasRights(this.client.getHabbo())) {
            String username = this.packet.readString();
            boolean accepted = this.packet.readBoolean();

            Habbo habbo = Emulator.getGameServer().getGameClientManager().getHabbo(username);

            if (habbo != null && habbo.getHabboInfo().getRoomQueueId() == this.client.getHabbo().getHabboInfo().getCurrentRoom().getId()) {
                this.client.getHabbo().getHabboInfo().getCurrentRoom().removeFromQueue(habbo);

                if (accepted) {
                    habbo.getClient().sendResponse(new FlatAccessibleComposer(habbo.getClient().getRevision(), this.client.getHabbo().getHabboInfo().getCurrentRoom().getId(), this.client.getHabbo().getHabboInfo().getUsername()));
                    Emulator.getGameEnvironment().getRoomManager().enterRoom(habbo, this.client.getHabbo().getHabboInfo().getCurrentRoom().getId(), "", true);
                } else {
                    habbo.getClient().sendResponse(new FlatAccessDeniedComposer(""));
                    habbo.getClient().sendResponse(new CloseConnectionComposer());
                }
                habbo.getHabboInfo().setRoomQueueId(0);
            }

        }
    }
}
