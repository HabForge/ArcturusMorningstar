package com.eu.habbo.util.packets.prediction.checkers;

import com.eu.habbo.util.packets.prediction.SPacket;

public class ByteChecker extends TypeChecker<Byte> {

    ByteChecker(SPacket hPacket) {
        super("b", hPacket);
    }

    @Override
    public boolean canRead(int index) {
        return index >= 0 && index < hPacket.getBytesLength();
    }

    @Override
    public double score(int index) {
        // A byte is never likely to be parsed as a literal byte, only rarely

        return 0.4;
    }

    @Override
    public Byte get(int index) {
        return hPacket.readByte(index);
    }

    @Override
    int nextIndexSafe(int index) {
        return index + 1;
    }
}
