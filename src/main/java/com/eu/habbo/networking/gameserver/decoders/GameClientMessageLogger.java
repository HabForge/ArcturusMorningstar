package com.eu.habbo.networking.gameserver.decoders;

import com.eu.habbo.messages.ClientMessage;
import com.eu.habbo.util.ANSI;
import com.eu.habbo.util.packets.PacketStringUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GameClientMessageLogger extends MessageToMessageDecoder<ClientMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameClientMessageLogger.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ClientMessage message, List<Object> out) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("[" + ANSI.GREEN + "CLIENT" + ANSI.DEFAULT + "][%-41s] => %s",
                    message.getHeader(),
                    PacketStringUtils.toString(message)));
        }

        out.add(message);
    }

}
