package com.eu.habbo.protocol;

import gnu.trove.map.hash.THashMap;

public enum Revision {

    DEFAULT("DEFAULT"),
    PRODUCTION_201611291003_338511768("PRODUCTION-201611291003-338511768"),
    WIN63_202408051224_787955622("WIN63-202408051224-787955622");

    private static final THashMap<String, Revision> revisions = new THashMap<>();

    static {
        for (Revision revision : values()) {
            revisions.put(revision.getVersion(), revision);
        }
    }

    public static Revision getRevision(String version) {
        return revisions.get(version);
    }

    private final String version;

    Revision(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    @Override
    public String toString() {
        return this.version;
    }
}
