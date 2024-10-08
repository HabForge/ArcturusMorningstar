package com.eu.habbo.messages.outgoing.landingview;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.hotelview.NewsList;
import com.eu.habbo.habbohotel.hotelview.NewsWidget;
import com.eu.habbo.messages.ServerMessage;
import com.eu.habbo.messages.outgoing.MessageComposer;
import com.eu.habbo.messages.outgoing.Outgoing;

public class PromoArticlesComposer extends MessageComposer {
    @Override
    protected ServerMessage composeInternal() {
        this.response.init(Outgoing.PromoArticles);
        NewsList newsList = Emulator.getGameEnvironment().getHotelViewManager().getNewsList();

        this.response.appendInt(newsList.getNewsWidgets().size());

        for (NewsWidget widget : newsList.getNewsWidgets()) {
            this.response.appendInt(widget.getId());
            this.response.appendString(widget.getTitle());
            this.response.appendString(widget.getMessage());
            this.response.appendString(widget.getButtonMessage());
            this.response.appendInt(widget.getType());
            this.response.appendString(widget.getLink());
            this.response.appendString(widget.getImage());
        }

        return this.response;
    }
}
