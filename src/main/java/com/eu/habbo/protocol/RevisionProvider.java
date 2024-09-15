package com.eu.habbo.protocol;

import com.eu.habbo.messages.incoming.Incoming;


public interface RevisionProvider {

    /**
     * Get the incoming header for the given revision and message id.
     *
     * @param revision The revision to get the header for.
     * @param messageId The message id to get the header for.
     * @return Returns the incoming header when found, otherwise null.
     */
    Incoming getIncoming(Revision revision, int messageId);
    //Outgoing getOutgoing(Revision revision, int messageId);

    int getMessageId(Revision revision, Incoming header);
    //int getMessageId(Revision revision, Outgoing header);

}
