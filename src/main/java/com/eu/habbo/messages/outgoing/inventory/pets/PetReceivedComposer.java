package com.eu.habbo.messages.outgoing.inventory.pets;

import com.eu.habbo.habbohotel.pets.Pet;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class PetReceivedComposer extends MessageComposer {
    private final Pet pet;
    private final boolean gift;

    public PetReceivedComposer(Pet pet, boolean gift) {
        this.pet = pet;
        this.gift = gift;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.PetReceived);
        this.response.appendBoolean(this.gift);
        this.pet.serialize(this.response);
        return this.response;
    }
}
