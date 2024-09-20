package com.eu.habbo.messages.outgoing.quest;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class EpicPopupComposer extends MessageComposer {
    public static final String LIBRARY_URL = "${image.library.url}";
    private final String assetURI;

    public EpicPopupComposer(String assetURI) {
        this.assetURI = assetURI;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.EpicPopup);
        this.response.appendString(this.assetURI);
        return this.response;
    }
}
