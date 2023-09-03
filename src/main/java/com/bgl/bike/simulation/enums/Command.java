package com.bgl.bike.simulation.enums;

import lombok.Getter;

@Getter
public enum Command {

    PLACE("PLACE"),
    FORWARD("FORWARD"),
    TURN_LEFT("TURN_LEFT"),
    TURN_RIGHT("TURN_RIGHT"),
    GPS_REPORT("GPS_REPORT");

    private String value;

    Command(String value) {
        this.value = value;
    }


}
