package com.eu.habbo.habbohotel.items;

import com.eu.habbo.Emulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CrackableReward {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrackableReward.class);

    public final int itemId;
    public final int count;
    public final Map<Integer, Map.Entry<String, Integer>> prizes;
    public final String achievementTick;
    public final String achievementCracked;
    public final int requiredEffect;
    public final int subscriptionDuration;
    public final RedeemableSubscriptionType subscriptionType;
    public int totalChance;

    public CrackableReward(ResultSet set) throws SQLException {
        this.itemId = set.getInt("item_id");
        this.count = set.getInt("count");
        this.achievementTick = set.getString("achievement_tick");
        this.achievementCracked = set.getString("achievement_cracked");
        this.requiredEffect = set.getInt("required_effect");
        this.subscriptionDuration = set.getInt("subscription_duration");
        this.subscriptionType =
                RedeemableSubscriptionType.fromString(set.getString("subscription_type"));

        String[] prizeEntries = set.getString("prizes").split(";");
        this.prizes = new HashMap<>();

        if (set.getString("prizes").isEmpty()) return;

        this.totalChance = 0;
        for (String prizeEntry : prizeEntries) {
            try {
                String[] parts = prizeEntry.split(":");
                String itemsPart = parts[0];
                int chance = (parts.length == 2) ? Integer.parseInt(parts[1]) : 100;

                this.prizes.put(this.totalChance, new AbstractMap.SimpleEntry<>(itemsPart, this.totalChance + chance));
                this.totalChance += chance;
            } catch (Exception e) {
                LOGGER.error("Caught exception", e);
            }
        }
    }

    public List<Integer> getRandomReward() {
        if (this.prizes.isEmpty()) return new ArrayList<>();

        Random random = new Random();
        int randInt = random.nextInt(this.totalChance);

        for (Map.Entry<Integer, Map.Entry<String, Integer>> entry : this.prizes.entrySet()) {
            Map.Entry<String, Integer> value = entry.getValue();
            if (randInt >= entry.getKey() && randInt < value.getValue()) {
                return parsePrizeString(value.getKey());
            }
        }

        return new ArrayList<>();
    }

    private List<Integer> parsePrizeString(String prizeString) {
        List<Integer> result = new ArrayList<>();
        String[] items = prizeString.split(",");

        for (String item : items) {
            if (item.contains("|")) {
                String[] parts = item.split("\\|");
                int itemId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                for (int i = 0; i < quantity; i++) {
                    result.add(itemId);
                }
            } else {
                int itemId = Integer.parseInt(item);
                result.add(itemId);
            }
        }

        return result;
    }
}

