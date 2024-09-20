package com.eu.habbo.messages.incoming.sound;

import com.eu.habbo.habbohotel.rooms.TraxManager;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.sound.JukeboxSongDisksComposer;
import com.eu.habbo.messages.outgoing.sound.UserSongDisksInventoryComposer;

public class GetNowPlayingEvent extends MessageHandler {
    @Override
    public void handle() throws Exception {
        TraxManager traxManager = this.client.getHabbo().getHabboInfo().getCurrentRoom().getTraxManager();
        this.client.sendResponse(new JukeboxSongDisksComposer(traxManager.getSongs(), traxManager.totalLength()));
        this.client.sendResponse(new UserSongDisksInventoryComposer(traxManager.myList(this.client.getHabbo())));
        this.client.getHabbo().getHabboInfo().getCurrentRoom().getTraxManager().updateCurrentPlayingSong(this.client.getHabbo());
    }
}
