package com.eu.habbo.messages;

import com.eu.habbo.protocol.Revision;

public interface ISerialize {

    void serialize(ServerMessage message);

    default void serialize(ServerMessage message, Revision revision) {
        this.serialize(message);
    }

}