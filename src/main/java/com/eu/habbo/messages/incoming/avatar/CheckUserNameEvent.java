package com.eu.habbo.messages.incoming.avatar;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.HabboManager;
import com.eu.habbo.messages.incoming.MessageHandler;
import com.eu.habbo.messages.outgoing.avatar.CheckUserNameResultComposer;

import java.util.ArrayList;
import java.util.List;

public class CheckUserNameEvent extends MessageHandler {
    public static String VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-=!?@:,.";

    @Override
    public void handle() throws Exception {
        if (!this.client.getHabbo().getHabboStats().allowNameChange)
            return;

        String name = this.packet.readString();

        int errorCode = CheckUserNameResultComposer.AVAILABLE;

        List<String> suggestions = new ArrayList<>(4);
        if (name.length() < 3) {
            errorCode = CheckUserNameResultComposer.TOO_SHORT;
        } else if (name.length() > 15) {
            errorCode = CheckUserNameResultComposer.TOO_LONG;
        } else if (name.equalsIgnoreCase(this.client.getHabbo().getHabboInfo().getUsername()) || HabboManager.getOfflineHabboInfo(name) != null || ChangeUserNameEvent.changingUsernames.contains(name.toLowerCase())) {
            errorCode = CheckUserNameResultComposer.TAKEN_WITH_SUGGESTIONS;
            suggestions.add(name + Emulator.getRandom().nextInt(9999));
            suggestions.add(name + Emulator.getRandom().nextInt(9999));
            suggestions.add(name + Emulator.getRandom().nextInt(9999));
            suggestions.add(name + Emulator.getRandom().nextInt(9999));
        } else if (!Emulator.getGameEnvironment().getWordFilter().filter(name, this.client.getHabbo()).equalsIgnoreCase(name)) {
            errorCode = CheckUserNameResultComposer.NOT_VALID;
        } else {
            String checkName = name;
            for (char c : VALID_CHARACTERS.toCharArray()) {
                checkName = checkName.replace(c + "", "");
            }

            if (!checkName.isEmpty()) {
                errorCode = CheckUserNameResultComposer.NOT_VALID;
            } else {
                this.client.getHabbo().getHabboStats().changeNameChecked = name;
            }
        }

        this.client.sendResponse(new CheckUserNameResultComposer(errorCode, name, suggestions));
    }
}
