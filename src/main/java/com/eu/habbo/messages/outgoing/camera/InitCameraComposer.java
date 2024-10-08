package com.eu.habbo.messages.outgoing.camera;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class InitCameraComposer extends MessageComposer {
    public final int credits;
    public final int points;
    public final int pointsType;

    public InitCameraComposer(int credits, int points, int pointsType) {
        this.credits = credits;
        this.points = points;
        this.pointsType = pointsType;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.InitCamera);
        this.response.appendInt(this.credits);
        this.response.appendInt(this.points);
        this.response.appendInt(this.pointsType);
        return this.response;
    }
}