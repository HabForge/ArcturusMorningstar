package com.eu.habbo.messages.outgoing.help;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

import java.util.List;

public class QuizResultsComposer extends MessageComposer {
    private final String unknownString;
    private final List<Integer> unknownIntegerList;

    public QuizResultsComposer(String unknownString, List<Integer> unknownIntegerList) {
        this.unknownString = unknownString;
        this.unknownIntegerList = unknownIntegerList;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.QuizResults);
        this.response.appendString(this.unknownString);
        this.response.appendInt(this.unknownIntegerList.size());
        for (Integer i : this.unknownIntegerList) {
            this.response.appendInt(i);
        }
        return this.response;
    }
}