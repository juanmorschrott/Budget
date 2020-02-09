package com.habitissimo.backend.model;

public enum State {
    PENDING("PENDING"),
    PUBLISHED("PUBLISHED"),
    DISCARTED("DISCARTED");

    private String state;

    State(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

}
