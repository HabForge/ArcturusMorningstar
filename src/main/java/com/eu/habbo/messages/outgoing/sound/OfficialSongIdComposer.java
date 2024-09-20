package com.eu.habbo.messages.outgoing.sound;

import com.eu.habbo.habbohotel.items.SoundTrack;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class OfficialSongIdComposer extends MessageComposer {
    private final SoundTrack track;

    public OfficialSongIdComposer(SoundTrack track) {
        this.track = track;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.OfficialSongId);
        this.response.appendString(this.track.getCode());
        this.response.appendInt(this.track.getId());
        return this.response;
    }
}
