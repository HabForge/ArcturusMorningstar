package com.eu.habbo.util.packets.prediction;

import com.eu.habbo.util.packets.prediction.checkers.TypeChecker;
import com.eu.habbo.util.packets.prediction.checkers.TypeCheckerProducer;

import java.util.List;

public class StructurePredictor {

    private final SPacket packet;
    private String structure; // null if not found/ didnt try to find

    public StructurePredictor(SPacket packet) {
        this.packet = packet;
        this.predictStructure();
    }

    private static class SubStructure {
        int previous;
        String type;
        double logScore;

        public SubStructure(int previous, String type, double logScore) {
            this.previous = previous;
            this.type = type;
            this.logScore = logScore;
        }
    }

    private void predictStructure() {
        List<TypeChecker> typeCheckers = TypeCheckerProducer.getValidators(packet);

        SubStructure[] dynamic = new SubStructure[packet.getBytesLength() + 1];
        dynamic[0] = new SubStructure(-1, "", 0.0);

        int index = 0;
        while (index < packet.getBytesLength()) {
            double currentLogScore = dynamic[index].logScore;
            for (TypeChecker typeChecker : typeCheckers) {
                if (!typeChecker.canRead(index)) continue;

                double newScore = currentLogScore + Math.log(typeChecker.score(index));
                int nextIndex = typeChecker.nextIndex(index);
                if (dynamic[nextIndex] == null || newScore > dynamic[nextIndex].logScore) {
                    dynamic[nextIndex] = new SubStructure(index,  typeChecker.getStructCode(), newScore);
                }
            }
            index++;
        }

        StringBuilder stringBuilder = new StringBuilder();
        SubStructure current = dynamic[dynamic.length - 1];
        while (current.previous != -1) {
            stringBuilder.append(current.type);
            current = dynamic[current.previous];
        }

        structure = stringBuilder.reverse().toString();
    }

    public String getStructure() {
        return structure;
    }
}
