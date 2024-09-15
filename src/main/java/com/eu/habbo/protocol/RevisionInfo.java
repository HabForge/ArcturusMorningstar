package com.eu.habbo.protocol;

public class RevisionInfo {

    private final Messages messages;

    public RevisionInfo(Messages messages) {
        this.messages = messages;
    }

    public Messages getMessages() {
        return messages;
    }

    public static class Messages {

        private final MessageInfo[] incoming;
        private final MessageInfo[] outgoing;

        public Messages(MessageInfo[] incoming, MessageInfo[] outgoing) {
            this.incoming = incoming;
            this.outgoing = outgoing;
        }

        public MessageInfo[] getIncoming() {
            return incoming;
        }

        public MessageInfo[] getOutgoing() {
            return outgoing;
        }

    }

    public static class MessageInfo {

        private final int id;
        private final String name;
        private final String asNamespace;
        private final String asClass;
        private final boolean confident;

        public MessageInfo(int id, String name, String asNamespace, String asClass, boolean confident) {
            this.id = id;
            this.name = name;
            this.asNamespace = asNamespace;
            this.asClass = asClass;
            this.confident = confident;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAsNamespace() {
            return asNamespace;
        }

        public String getAsClass() {
            return asClass;
        }

        public boolean isConfident() {
            return confident;
        }
    }

}
