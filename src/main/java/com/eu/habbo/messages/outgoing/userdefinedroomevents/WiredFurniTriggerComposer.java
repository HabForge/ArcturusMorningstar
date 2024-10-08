package com.eu.habbo.messages.outgoing.userdefinedroomevents;

import com.eu.habbo.habbohotel.items.interactions.InteractionWiredTrigger;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class WiredFurniTriggerComposer extends MessageComposer {
    private final InteractionWiredTrigger trigger;
    private final Room room;

    public WiredFurniTriggerComposer(InteractionWiredTrigger trigger, Room room) {
        this.trigger = trigger;
        this.room = room;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.WiredFurniTrigger);
        this.trigger.serializeWiredData(this.response, this.room);
        this.trigger.needsUpdate(true);
        return this.response;
    }
}
