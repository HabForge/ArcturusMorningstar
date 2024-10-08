package com.eu.habbo.messages.outgoing.room.pets;

import com.eu.habbo.habbohotel.pets.Pet;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class PetExperienceComposer extends MessageComposer {
    private final Pet pet;
    private final int amount;

    public PetExperienceComposer(Pet pet, int amount) {
        this.pet = pet;
        this.amount = amount;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.PetExperience);
        this.response.appendInt(this.pet.getId());
        this.response.appendInt(this.pet.getRoomUnit().getId());
        this.response.appendInt(this.amount);
        return this.response;
    }
}
