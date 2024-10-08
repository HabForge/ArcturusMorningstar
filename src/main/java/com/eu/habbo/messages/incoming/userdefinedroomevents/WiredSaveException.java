package com.eu.habbo.messages.incoming.userdefinedroomevents;

public class WiredSaveException extends Exception {

    private final String message;

    public WiredSaveException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
