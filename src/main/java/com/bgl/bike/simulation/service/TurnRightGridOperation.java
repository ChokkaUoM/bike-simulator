package com.bgl.bike.simulation.service;

import com.bgl.bike.simulation.model.Grid;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Builder
public class TurnRightGridOperation implements GridOperation {

    private Grid grid;

    @Override
    public Grid execute() {
        return grid.turnRight();
    }
}
