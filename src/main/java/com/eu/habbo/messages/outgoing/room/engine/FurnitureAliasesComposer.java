package com.eu.habbo.messages.outgoing.room.engine;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

import java.util.Map;

public class FurnitureAliasesComposer extends MessageComposer {
    private final Map<String, String> unknownMap;

    public FurnitureAliasesComposer(Map<String, String> unknownMap) {
        this.unknownMap = unknownMap;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.FurnitureAliases);
        this.response.appendInt(this.unknownMap.size());
        for (Map.Entry<String, String> entry : this.unknownMap.entrySet()) {
            this.response.appendString(entry.getKey());
            this.response.appendString(entry.getValue());
        }
        return this.response;
    }
}