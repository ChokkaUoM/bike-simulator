package com.bgl.bike.simulation.service;

import com.bgl.bike.simulation.model.Grid;

@FunctionalInterface
public interface GridOperation {

    Grid execute();
}
