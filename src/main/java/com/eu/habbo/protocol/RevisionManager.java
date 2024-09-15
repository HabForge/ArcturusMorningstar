package com.eu.habbo.protocol;

import com.eu.habbo.messages.incoming.Incoming;
import com.google.gson.Gson;
import gnu.trove.map.hash.THashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;

public class RevisionManager implements RevisionProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(RevisionManager.class);
    private static final Gson GSON = new Gson();

    private final THashMap<Revision, RevisionMap<Incoming>> revisionIncoming;

    public RevisionManager() {
        this.revisionIncoming = new THashMap<>();
    }

    @Override
    public Incoming getIncoming(Revision revision, int messageId) {
        final RevisionMap<Incoming> map = revisionIncoming.get(revision);

        if (map == null) {
            return null;
        }

        return map.idToHeader.get(messageId);
    }

    @Override
    public int getMessageId(Revision revision, Incoming header) {
        return 0;
    }

    //public void getMessageId(Outgoing header) {
    //
    //}

    public void load() {
        LOGGER.info("Loading revisions");

        for (Revision revision : Revision.values()) {
            if (revision.getVersion() == null) {
                continue;
            }

            loadRevision(revision);
        }
    }

    private void loadRevision(Revision revision) {
        LOGGER.info("Loading revision {}", revision.getVersion());

        final String path = String.format("/revisions/%s.json", revision.getVersion());

        try (final InputStream stream = RevisionManager.class.getResourceAsStream(path)) {
            if (stream == null) {
                LOGGER.error("Revision {} not found", revision.getVersion());
                return;
            }

            // Parse info
            final InputStreamReader reader = new InputStreamReader(stream);
            final RevisionInfo info = GSON.fromJson(reader, RevisionInfo.class);

            addRevision(revision, info);
        } catch (Exception e) {
            LOGGER.error("Failed to load revision {}", revision.getVersion(), e);
        }
    }

    private void addRevision(Revision revision, RevisionInfo info) {
        final RevisionMap<Incoming> incomingMap = new RevisionMap<>();

        // To future readers, these are swapped because they were dumped from a client.
        // As a server we use them the other way around.
        fillMap(revision, Incoming.class, incomingMap, info.getMessages().getOutgoing());
        //fillMap(revision, Outgoing.class, outgoingMap, info.getMessages().getIncoming());

        this.revisionIncoming.put(revision, incomingMap);
    }

    private <T extends Enum<T>> void fillMap(Revision revision, Class<T> clazz, RevisionMap<T> revisionMap, RevisionInfo.MessageInfo[] messages) {
        for (RevisionInfo.MessageInfo message : messages) {
            if (!message.isConfident()) {
                continue;
            }

            try {
                final String headerName = message.getName().replace("MessageComposer", "");
                final T header = Enum.valueOf(clazz, headerName);

                revisionMap.headerToId.put(header, message.getId());
                revisionMap.idToHeader.put(message.getId(), header);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Unable to map {} message {} {}", clazz.getName(), revision, message.getName());
            }
        }
    }
}
