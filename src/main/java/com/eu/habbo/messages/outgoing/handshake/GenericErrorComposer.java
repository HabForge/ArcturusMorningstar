package com.eu.habbo.messages.outgoing.handshake;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class GenericErrorComposer extends MessageComposer {
    public static final int AUTHENTICATION_FAILED = -3;
    public static final int CONNECTING_TO_THE_SERVER_FAILED = -400;
    public static final int KICKED_OUT_OF_THE_ROOM = 4008;
    public static final int NEED_TO_BE_VIP = 4009;
    public static final int ROOM_NAME_UNACCEPTABLE = 4010;
    public static final int CANNOT_BAN_GROUP_MEMBER = 4011;
    public static final int WRONG_PASSWORD_USED = -100002;

    private final int errorCode;

    public GenericErrorComposer(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.GenericError);
        this.response.appendInt(this.errorCode);
        return this.response;
    }
}
