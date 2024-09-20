package com.eu.habbo.messages.outgoing.sound;

import com.eu.habbo.habbohotel.items.interactions.InteractionMusicDisc;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

import java.util.List;

public class JukeboxSongDisksComposer extends MessageComposer {
    private final List<InteractionMusicDisc> songs;
    private final int totalLength;

    public JukeboxSongDisksComposer(List<InteractionMusicDisc> songs, int totalLength) {
        this.songs = songs;
        this.totalLength = totalLength;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.JukeboxSongDisks);
        this.response.appendInt(this.totalLength); //Dunno //TODO Total play length?
        this.response.appendInt(this.songs.size());
        for (InteractionMusicDisc soundTrack : this.songs) {
            this.response.appendInt(soundTrack.getId());
            this.response.appendInt(soundTrack.getSongId());
        }
        return this.response;
    }
}
