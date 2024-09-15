package com.eu.habbo.networking.gameserver.encoders;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.networking.gameserver.GameServerAttributes;
import com.eu.habbo.protocol.RevisionException;
import com.eu.habbo.protocol.RevisionProvider;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.IllegalReferenceCountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GameServerMessageEncoder extends MessageToByteEncoder<ServerMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameServerMessageEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, ServerMessage message, ByteBuf out) throws Exception {
        try {
            final GameClient client = ctx.channel().attr(GameServerAttributes.CLIENT).get();
            final RevisionProvider revisionProvider = ctx.channel().attr(GameServerAttributes.REVISION_PROVIDER).get();

            final ByteBuf buf = message.get();

            // Buf is a copy so we can freely modify it to inject the correct packet id.
            buf.setShort(4, revisionProvider.getMessageId(client.getRevision(), message.getHeader()));

            try {
                out.writeBytes(buf);
            } finally {
                // Release copied buffer.
                buf.release();
            }
        } catch (RevisionException e) {
            LOGGER.error("Failed to send message", e);
        } catch (IllegalReferenceCountException e) {
            throw new IOException(String.format("IllegalReferenceCountException happened for ServerMessage with packet id %s.", message.getHeader()), e);
        }
    }
}
