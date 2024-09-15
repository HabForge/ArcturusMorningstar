package com.eu.habbo.protocol;

import com.eu.habbo.messages.incoming.Incoming;
import com.eu.habbo.messages.outgoing.Outgoing;


public interface RevisionProvider {

    /**
     * Get the incoming header for the given revision and message id.
     *
     * @param revision The revision to get the header for.
     * @param messageId The message id to get the header for.
     * @return Returns the incoming header when found, otherwise null.
     */
    Incoming getIncoming(Revision revision, int messageId);

    /**
     * Get the outgoing header for the given revision and message id.
     *
     * @param revision The revision to get the header for.
     * @param messageId The message id to get the header for.
     * @return Returns the outgoing header when found, otherwise null.
     */
    Outgoing getOutgoing(Revision revision, int messageId);

    int getMessageId(Revision revision, Incoming header) throws RevisionException;
    int getMessageId(Revision revision, Outgoing header) throws RevisionException;

}
