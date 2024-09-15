package com.eu.habbo.messages.incoming.handshake;

import com.eu.habbo.Emulator;
import com.eu.habbo.messages.NoAuthMessage;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.handshake.InitDiffieHandshakeComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoAuthMessage
public class InitDiffieHandshakeEvent extends MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDiffieHandshakeEvent.class);

    @Override
    public void handle() throws Exception {
        if (this.client.getEncryption() == null) {
            LOGGER.debug("Client tried to start Diffie-Hellman handshake, but encryption is not initialized");
            Emulator.getGameServer().getGameClientManager().disposeClient(this.client);
            return;
        }

        this.client.sendResponse(new InitDiffieHandshakeComposer(
                this.client.getEncryption().getDiffie().getSignedPrime(),
                this.client.getEncryption().getDiffie().getSignedGenerator()));
    }

}
