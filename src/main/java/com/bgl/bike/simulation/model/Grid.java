package com.bgl.bike.simulation.model;

import com.bgl.bike.simulation.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grid {

    private int rows;
    private int cols;
    private Position startPosition;
    private Position currentPosition;

    public Grid place() {
        if ( startPosition.getCurrentLocation().getX() < 0 || startPosition.getCurrentLocation().getX() > rows
        || startPosition.getCurrentLocation().getY() < 0 || startPosition.getCurrentLocation().getY() > cols) {
            log.error("Can not place. Going out of the grid");
        } else {
            this.currentPosition = this.startPosition;
        }

        return this;
    }

    public Grid forward() {

        int x = currentPosition.getCurrentLocation().getX();
        int y = currentPosition.getCurrentLocation().getY();

        switch (currentPosition.getDirection()) {
            case NORTH:
                y = y + 1;
                break;
            case SOUTH:
                y = y - 1;
                break;
            case EAST:
                x = x + 1;
                break;
            case WEST:
                x = x - 1;
                break;
        }

        Point updatedPoint = Point.builder().x(x).y(y).build();

        if (isValidPoint(updatedPoint)) {
            this.currentPosition.setCurrentLocation(updatedPoint);
        } else {
            log.error("Invalid request. Cannot go FORWARD");
        }

        return this;
    }

    public Grid turnLeft() {
        switch (currentPosition.getDirection()) {
            case NORTH:
                currentPosition.setDirection(Direction.WEST);
                break;
            case SOUTH:
                currentPosition.setDirection(Direction.EAST);
                break;
            case EAST:
                currentPosition.setDirection(Direction.NORTH);
                break;
            case WEST:
                currentPosition.setDirection(Direction.SOUTH);
                break;
        }

        return this;
    }

    public Grid turnRight() {
        switch (currentPosition.getDirection()) {
            case NORTH:
                currentPosition.setDirection(Direction.EAST);
                break;
            case SOUTH:
                currentPosition.setDirection(Direction.WEST);
                break;
            case EAST:
                currentPosition.setDirection(Direction.SOUTH);
                break;
            case WEST:
                currentPosition.setDirection(Direction.NORTH);
                break;
        }

        return this;
    }

    public Grid printGPSReport() {
        log.info("({},{}),{}", currentPosition.getCurrentLocation().getX(), currentPosition.getCurrentLocation().getY(),
                currentPosition.getDirection());

        return this;
    }

    private boolean isValidPoint(Point point) {
        if (point.getX() < 0 || point.getX() > rows || point.getY() < 0 || point.getY() > cols) {
            return false;
        }
        return true;
    }


}
