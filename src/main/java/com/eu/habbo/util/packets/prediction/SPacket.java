package com.eu.habbo.util.packets.prediction;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SPacket {

    private final ByteBuf packet;
    private final byte[] packetBytes;
    private final int length;

    public SPacket(ByteBuf packet) {
        this.packet = packet;
        this.packetBytes = new byte[packet.readableBytes()];
        this.length = packet.readableBytes();

        packet.getBytes(0, this.packetBytes);
    }

    public boolean readBoolean() {
        return (readByte() != 0);
    }

    public boolean readBoolean(int index) {
        return (readByte(index) != 0);
    }

    public byte readByte() {
        return this.packet.readByte();
    }

    public byte readByte(int index) {
        return this.packet.getByte(index);
    }

    public byte[] readBytes(int length, int index) {
        byte[] newbytes = new byte[length];
        for (int i = 0; i < (length); i++) {
            newbytes[i] = this.packetBytes[i + index];
        }
        return newbytes;
    }

    public int readShort() {
        return this.packet.readUnsignedShort();
    }

    public int readShort(int index) {
        return this.packet.getUnsignedShort(index);
    }

    public int readInteger() {
        return this.packet.readInt();
    }

    public int readInteger(int index) {
        return this.packet.getInt(index);
    }

    public String readString() {
        return readString(StandardCharsets.ISO_8859_1);
    }

    public String readString(Charset charset) {
        final int ridx = this.packet.readerIndex();
        String r = readString(ridx, charset);
        this.packet.readerIndex(ridx + 2 + this.readShort(ridx));
        return r;
    }

    public String readString(int index) {
        return readString(index, StandardCharsets.ISO_8859_1);
    }

    public String readString(int index, Charset charset) {
        int length = readShort(index);
        index += 2;
        return readString(index, length, charset);
    }

    private String readString(int index, int length, Charset charset) {
        byte[] x = new byte[length];
        for (int i = 0; i < x.length; i++) {
            x[i] = readByte(index);
            index++;
        }
        return new String(x, charset);
    }

    public byte[] toBytes() {
        return this.packetBytes;
    }

    public int getBytesLength() {
        return this.length;
    }

    public void resetReaderIndex() {
        this.packet.resetReaderIndex();
    }
}
