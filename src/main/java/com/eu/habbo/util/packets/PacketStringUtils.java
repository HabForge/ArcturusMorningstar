package com.eu.habbo.util.packets;

import com.eu.habbo.messages.ClientMessage;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.util.packets.prediction.SPacket;
import com.eu.habbo.util.packets.prediction.StructurePredictor;
import io.netty.buffer.ByteBuf;

public class PacketStringUtils {

    public static String toString(ClientMessage message) {
        final ByteBuf copy = message.getBuffer().copy();

        try {
            return toString(new SPacket(copy));
        } finally {
            copy.release();
        }
    }

    public static String toString(ServerMessage message) {
        ByteBuf copy = message.get().copy();

        copy.skipBytes(6);
        copy = copy.discardReadBytes();

        try {
            return toString(new SPacket(copy));
        } finally {
            copy.release();
        }
    }

    private static String toString(SPacket packet) {
        try {
            final StructurePredictor predictor = new StructurePredictor(packet);
            final String structure = predictor.getStructure();

            if (structure == null) {
                return "Unknown packet structure";
            }

            return toExpressionFromGivenStructure(packet, predictor.getStructure());
        } catch (Exception e) {
            return "Failed to parse packet structure";
        }
    }

    private static String toExpressionFromGivenStructure(SPacket packet, String struct) {
        packet.resetReaderIndex();

        final StringBuilder builder = new StringBuilder();

        buildExpressionFromGivenStructure(packet, struct, builder);

        return builder.toString();
    }

    private static void buildExpressionFromGivenStructure(SPacket p, String struct, StringBuilder builder) {
        int index = 0;

        while (index < struct.length()) {
            char c = struct.charAt(index++);
            if (c == 'i') builder.append("{i:").append(p.readInteger()).append('}');
            else if (c == 's') builder.append("{s:\"").append(
                    p.readString()
                            .replace("\\", "\\\\")  // \ -> \\
                            .replace("\"", "\\\"")  // " -> \"
                            .replace("\r", "\\r")   // CR -> \r
            ).append("\"}");
            else if (c == 'b') builder.append("{b:").append((((int)(p.readByte())) + 256) % 256).append('}');
            else if (c == 'B') builder.append("{b:").append(p.readBoolean()).append('}');
            else if (c == 'u') builder.append("{u:").append(p.readShort()).append('}');
            else return;
        }
    }
}
