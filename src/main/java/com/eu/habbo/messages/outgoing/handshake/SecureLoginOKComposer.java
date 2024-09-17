package com.eu.habbo.messages.outgoing.handshake;

import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class SecureLoginOKComposer extends MessageComposer {
    private final int accountId;
    private final short[] suggestedLoginActions;
    private final int identityId;

    public SecureLoginOKComposer(int accountId, short[] suggestedLoginActions, int identityId) {
        this.accountId = accountId;
        this.suggestedLoginActions = suggestedLoginActions;
        this.identityId = identityId;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.AuthenticationOK);
        this.response.appendInt(this.accountId);

        if (this.suggestedLoginActions != null) {
            this.response.appendInt(this.suggestedLoginActions.length);

            for (short x : this.suggestedLoginActions) {
                this.response.appendShort(x);
            }
        } else {
            this.response.appendInt(0);
        }

        this.response.appendInt(this.identityId);

        return this.response;
    }
}
