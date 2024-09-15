package com.eu.habbo.networking.gameserver.decoders;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.messages.ClientMessage;
import com.eu.habbo.messages.PacketManager;
import com.eu.habbo.networking.gameserver.GameServerAttributes;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.handler.ssl.NotSslRecordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@ChannelHandler.Sharable
public class GameMessageHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameMessageHandler.class);
    private final PacketManager packetManager;

    public GameMessageHandler() {
        this.packetManager = Emulator.getGameServer().getPacketManager();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        if (!Emulator.getGameServer().getGameClientManager().addClient(ctx)) {
            ctx.channel().close();
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        try {
            final ClientMessage message = (ClientMessage) msg;
            final GameClient client = ctx.channel().attr(GameServerAttributes.CLIENT).get();

            if (client != null) {
                this.packetManager.handlePacket(client, message);
            }
        } catch (Exception e) {
            LOGGER.error("Caught exception", e);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof IOException) {
            ctx.channel().close();
            return;
        }
        if (Emulator.getConfig().getBoolean("debug.mode")) {
            if (cause instanceof NotSslRecordException) {
                LOGGER.error("Plaintext received instead of ssl, closing channel");
            }
            else if (cause instanceof DecoderException) {
                LOGGER.error("Failed to decode packets, closing channel", cause);
            }
            else if (cause instanceof TooLongFrameException) {
                LOGGER.error("Disconnecting client, reason " + cause.getMessage());
            }
            else if (cause instanceof SSLHandshakeException) {
                LOGGER.error("URL Request error from source " + ctx.channel().remoteAddress());
            }
            else if (cause instanceof NoSuchAlgorithmException) {
                LOGGER.error("Invalid SSL algorithm, only TLSv1.2 supported in the request");
            }
            else if (cause instanceof KeyManagementException) {
                LOGGER.error("Invalid SSL algorithm, only TLSv1.2 supported in the request");
            }
            else if (cause instanceof UnsupportedMessageTypeException) {
                LOGGER.error("There was an illegal SSL request from (X-forwarded-for/CF-Connecting-IP has not being injected yet!) " + ctx.channel().remoteAddress());
            }
            else if (cause instanceof SSLException) {
                LOGGER.error("SSL Problem: "+ cause.getMessage() + cause);
            }
            else {
                LOGGER.error("Disconnecting client, exception in GameMessageHandler.", cause);
            }
        }
        ctx.channel().close();
    }
}
