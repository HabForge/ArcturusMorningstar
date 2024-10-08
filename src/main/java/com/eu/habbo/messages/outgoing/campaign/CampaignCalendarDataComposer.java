package com.eu.habbo.messages.outgoing.campaign;

import com.eu.habbo.habbohotel.campaign.calendar.CalendarRewardClaimed;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;
import gnu.trove.list.array.TIntArrayList;

import java.util.ArrayList;

public class CampaignCalendarDataComposer extends MessageComposer {
    private final String eventName;
    private final String campaignImage;
    private final int totalDays;
    private final int currentDay;
    private final ArrayList<CalendarRewardClaimed> unlocked;
    private final boolean lockExpired;

    public CampaignCalendarDataComposer(String eventName, String campaignImage, int totalDays, int currentDay, ArrayList<CalendarRewardClaimed> unlocked, boolean lockExpired) {
        this.eventName = eventName;
        this.campaignImage = campaignImage;
        this.totalDays = totalDays;
        this.currentDay = currentDay;
        this.unlocked = unlocked;
        this.lockExpired = lockExpired;
    }

    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.CampaignCalendarData);
        this.response.appendString(this.eventName);
        this.response.appendString(this.campaignImage);
        this.response.appendInt(this.currentDay);
        this.response.appendInt(this.totalDays);
        this.response.appendInt(this.unlocked.size());

        TIntArrayList expired = new TIntArrayList();
        if (this.lockExpired) { for (int i = 0; i < this.totalDays; i++) {
            expired.add(i);
        }
        }
        expired.remove(this.currentDay);
        if(this.currentDay > 1) expired.remove(this.currentDay - 2);
        if(this.currentDay > 0) expired.remove(this.currentDay - 1);

        this.unlocked.forEach(claimed -> {
            CampaignCalendarDataComposer.this.response.appendInt(claimed.getDay());
            expired.remove(claimed.getDay());
        });


        if (this.lockExpired) {
            this.response.appendInt(expired.size());
            expired.forEach(value -> {
                CampaignCalendarDataComposer.this.response.appendInt(value);
                return true;
            });
        } else {
            this.response.appendInt(0);
        }

        return this.response;
    }
}