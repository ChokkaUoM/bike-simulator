package com.bgl.bike.simulation.model;

import com.bgl.bike.simulation.enums.Direction;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class Position {

    private Point currentLocation;
    private Direction direction;
}
