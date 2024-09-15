package com.eu.habbo.protocol;

import gnu.trove.map.hash.THashMap;

public class RevisionMap<T extends Enum<?>> {

    public final THashMap<T, Integer> headerToId = new THashMap<>();
    public final THashMap<Integer, T> idToHeader = new THashMap<>();

}
