package com.bgl.bike.simulation.service;

import com.bgl.bike.simulation.model.Grid;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class GridOperationExecutor {

    public Grid executeOperation(GridOperation gridOperation) {
        return gridOperation.execute();
    }
}
