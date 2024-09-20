package com.eu.habbo.messages.outgoing.help;

import com.eu.habbo.habbohotel.guides.GuideChatMessage;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GuideSessionMessageComposer extends MessageComposer {
    private final GuideChatMessage message;

    public GuideSessionMessageComposer(GuideChatMessage message) {
        this.message = message;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GuideSessionMessage);
        this.response.appendString(this.message.message); //Message
        this.response.appendInt(this.message.userId);   //Sender ID
        return this.response;
    }
}
