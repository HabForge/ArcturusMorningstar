package com.eu.habbo.messages.incoming.room.engine;

import com.eu.habbo.habbohotel.pets.MonsterplantPet;
import com.eu.habbo.habbohotel.pets.Pet;
import com.eu.habbo.messages.incoming.MessageHandler;

public class TogglePetBreedingPermissionEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        int petId = this.packet.readInt();

        Pet pet = this.client.getHabbo().getHabboInfo().getCurrentRoom().getPet(petId);

        if (pet != null) {
            if (pet.getUserId() == this.client.getHabbo().getHabboInfo().getId()) {
                if (pet instanceof MonsterplantPet) {
                    ((MonsterplantPet) pet).setPubliclyBreedable(((MonsterplantPet) pet).isPubliclyBreedable());
                }
            }
        }
    }
}