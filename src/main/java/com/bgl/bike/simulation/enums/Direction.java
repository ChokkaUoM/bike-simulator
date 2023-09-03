package com.bgl.bike.simulation.enums;

import lombok.Getter;

@Getter
public enum Direction {

    NORTH("NORTH"),
    SOUTH("SOUTH"),
    EAST("EAST"),
    WEST("WEST");

    private String value;

    Direction(String value) {
        this.value = value;
    }
}
