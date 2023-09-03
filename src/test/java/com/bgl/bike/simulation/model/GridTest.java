package com.bgl.bike.simulation.model;

import com.bgl.bike.simulation.enums.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GridTest {

    private Grid grid;

    @BeforeEach
    public void setUp() {
        grid = Grid.builder()
                .rows(7)
                .cols(7)
                .startPosition(Position.builder()
                        .direction(Direction.NORTH)
                        .currentLocation(Point.builder()
                                .x(0)
                                .y(5)
                                .build())
                        .build()).build();
        grid.place();
    }

    @Test
    void place() {
        Grid updatedGrid = grid.place();
        Assertions.assertNotNull(updatedGrid);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getDirection(), Direction.NORTH);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getX(), 0);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getY(), 5);
    }

    @Test
    void forward() {
        Grid updatedGrid = grid.forward();
        Assertions.assertNotNull(updatedGrid);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getDirection(), Direction.NORTH);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getX(), 0);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getY(), 6);
    }

    @Test
    void turnLeft() {
        Grid updatedGrid = grid.turnLeft();
        Assertions.assertNotNull(updatedGrid);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getDirection(), Direction.WEST);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getX(), 0);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getY(), 5);
    }

    @Test
    void turnRight() {
        Grid updatedGrid = grid.turnRight();
        Assertions.assertNotNull(updatedGrid);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getDirection(), Direction.EAST);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getX(), 0);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getY(), 5);
    }

    @Test
    void printGPSReport() {
        grid.forward();
        grid.turnRight();
        grid.forward();
        Grid updatedGrid = grid.printGPSReport();
        Assertions.assertNotNull(updatedGrid);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getDirection(), Direction.EAST);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getX(), 1);
        Assertions.assertEquals(updatedGrid.getCurrentPosition().getCurrentLocation().getY(), 6);
    }
}