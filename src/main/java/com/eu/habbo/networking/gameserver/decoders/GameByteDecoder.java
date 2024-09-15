package com.eu.habbo.networking.gameserver.decoders;

import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.messages.ClientMessage;
import com.eu.habbo.messages.incoming.Incoming;
import com.eu.habbo.networking.gameserver.GameServerAttributes;
import com.eu.habbo.protocol.RevisionProvider;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GameByteDecoder extends ByteToMessageDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameByteDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        final GameClient client = ctx.channel().attr(GameServerAttributes.CLIENT).get();
        final RevisionProvider revisionProvider = ctx.channel().attr(GameServerAttributes.REVISION_PROVIDER).get();

        final short messageId = in.readShort();
        final Incoming header = revisionProvider.getIncoming(client.getRevision(), messageId);

        if (header == null) {
            LOGGER.warn("Unknown message id {} for protocol {}", messageId, client.getRevision());
            return;
        }

        ByteBuf body = Unpooled.copiedBuffer(in.readBytes(in.readableBytes()));

        out.add(new ClientMessage(header, body));
    }
}