package com.eu.habbo.core.consolecommands;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.catalog.CatalogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ConsoleInfoCommand extends ConsoleCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleInfoCommand.class);

    public ConsoleInfoCommand() {
        super("info", "Show current statistics.");
    }

    @Override
    public void handle(String[] args) throws Exception {
        int seconds = Emulator.getIntUnixTimestamp() - Emulator.getTimeStarted();
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);

        LOGGER.info("Emulator version: " + Emulator.VERSION);
        LOGGER.info("");
        LOGGER.info("Hotel Statistics");
        LOGGER.info("- Users: " + Emulator.getGameEnvironment().getHabboManager().getOnlineCount());
        LOGGER.info("- Rooms: " + Emulator.getGameEnvironment().getRoomManager().getActiveRooms().size());
        LOGGER.info("- Shop:  " + Emulator.getGameEnvironment().getCatalogManager().catalogPages.size() + " pages and " + CatalogManager.catalogItemAmount + " items.");
        LOGGER.info("- Furni: " + Emulator.getGameEnvironment().getItemManager().getItems().size() + " items.");
        LOGGER.info("");
        LOGGER.info("Server Statistics");
        LOGGER.info("- Uptime: " + day + (day > 1 ? " days, " : " day, ") + hours + (hours > 1 ? " hours, " : " hour, ") + minute + (minute > 1 ? " minutes, " : " minute, ") + second + (second > 1 ? " seconds!" : " second!"));
        LOGGER.info("- RAM Usage: " + (Emulator.getRuntime().totalMemory() - Emulator.getRuntime().freeMemory()) / (1024 * 1024) + "/" + (Emulator.getRuntime().freeMemory()) / (1024 * 1024) + "MB");
        LOGGER.info("- CPU Cores: " + Emulator.getRuntime().availableProcessors());
        LOGGER.info("- Total Memory: " + Emulator.getRuntime().maxMemory() / (1024 * 1024) + "MB");
    }
}