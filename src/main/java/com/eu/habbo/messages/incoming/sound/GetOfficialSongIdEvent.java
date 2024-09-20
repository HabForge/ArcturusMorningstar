package com.eu.habbo.messages.incoming.sound;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.items.SoundTrack;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.sound.OfficialSongIdComposer;

public class GetOfficialSongIdEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        String songName = this.packet.readString();

        final SoundTrack track = Emulator.getGameEnvironment().getItemManager().getSoundTrack(songName);

        if (track != null) {
            this.client.sendResponse(new OfficialSongIdComposer(track));
        }
    }
}
