package com.eu.habbo.util.packets.prediction.checkers;

import com.eu.habbo.util.packets.prediction.SPacket;

public abstract class TypeChecker<T> {

    protected String structCode;
    protected SPacket hPacket;

    protected TypeChecker(String structCode, SPacket hPacket) {
        this.structCode = structCode;
        this.hPacket = hPacket;
    }

    public abstract boolean canRead(int index);

    public abstract double score(int index);

    abstract T get(int index);

    // -1 if cant read
    public int nextIndex(int index) {
        if (!canRead(index)) return -1;
        return nextIndexSafe(index);
    }

    abstract int nextIndexSafe(int index);

    public String getStructCode() {
        return structCode;
    }

}
