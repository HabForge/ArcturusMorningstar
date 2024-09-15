package com.eu.habbo.util.packets.prediction.checkers;

import com.eu.habbo.util.packets.prediction.SPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeCheckerProducer {

    public static List<TypeChecker> getValidators(SPacket packet) {
        List<TypeChecker> typeCheckers = new ArrayList<>(Arrays.asList(
                new BooleanChecker(packet),
                new ByteChecker(packet),
                new IntegerChecker(packet),
                new StringChecker(packet)));

        return typeCheckers;
    }

}
