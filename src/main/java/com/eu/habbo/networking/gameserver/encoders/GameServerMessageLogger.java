package com.eu.habbo.networking.gameserver.encoders;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.util.ANSI;
import com.eu.habbo.util.packets.PacketStringUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GameServerMessageLogger extends MessageToMessageEncoder<ServerMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameServerMessageLogger.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, ServerMessage message, List<Object> out) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("[" + ANSI.BLUE + "SERVER" + ANSI.DEFAULT + "][%-41s] => %s",
                    message.getHeader(),
                    PacketStringUtils.toString(message)));
        }

        out.add(message);
    }

}