package com.eu.habbo.messages.outgoing.room.furniture;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class YoutubeControlVideoComposer extends MessageComposer {
    private final int furniId;
    private final int state;

    public YoutubeControlVideoComposer(int furniId, int state) {
        this.furniId = furniId;
        this.state = state;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.YoutubeControlVideo);
        this.response.appendInt(this.furniId);
        this.response.appendInt(this.state);

        return this.response;
    }
}
