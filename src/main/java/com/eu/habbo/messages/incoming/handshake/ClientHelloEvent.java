package com.eu.habbo.messages.incoming.handshake;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.NoAuthMessage;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.protocol.Revision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoAuthMessage
public class ClientHelloEvent extends MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHelloEvent.class);

    @Override
    public void handle() throws Exception {
        // Check if client has already set a revision.
        if (this.client.getRevision() != Revision.DEFAULT) {
            LOGGER.warn("Client tried to set revision twice.");
            Emulator.getGameServer().getGameClientManager().disposeClient(this.client);
            return;
        }

        final String revision = this.packet.readString();
        final String protocol = this.packet.readString();
        final int platform = this.packet.readInt();
        final int unknown = this.packet.readInt();

        final Revision parsedRevision = Revision.getRevision(revision);

        // Check revision.
        if (parsedRevision == null) {
            LOGGER.warn("Client tried to set invalid revision: {}", revision);
            Emulator.getGameServer().getGameClientManager().disposeClient(this.client);
            return;
        }

        if (parsedRevision == Revision.DEFAULT) {
            LOGGER.warn("Client tried to set unknown revision: {}", revision);
            Emulator.getGameServer().getGameClientManager().disposeClient(this.client);
            return;
        }

        // Update the client's revision.
        LOGGER.debug("Client set revision: {}", parsedRevision);

        this.client.setRevision(parsedRevision);
    }
}
