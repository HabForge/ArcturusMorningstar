package com.eu.habbo.messages.outgoing.sound;

import com.eu.habbo.habbohotel.items.SoundTrack;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

import java.util.List;

public class TraxSongInfoComposer extends MessageComposer {
    private final List<SoundTrack> tracks;

    public TraxSongInfoComposer(List<SoundTrack> tracks) {
        this.tracks = tracks;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.TraxSongInfo);
        this.response.appendInt(this.tracks.size());

        for (SoundTrack track : this.tracks) {
            this.response.appendInt(track.getId());
            this.response.appendString(track.getCode());
            this.response.appendString(track.getName());
            this.response.appendString(track.getData());
            this.response.appendInt(track.getLength() * 1000);
            this.response.appendString(track.getAuthor());
        }

        return this.response;
    }
}
