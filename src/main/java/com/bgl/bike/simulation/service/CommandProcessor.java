package com.bgl.bike.simulation.service;

import com.bgl.bike.simulation.enums.Command;
import com.bgl.bike.simulation.enums.Direction;
import com.bgl.bike.simulation.exceptions.InvalidInputException;
import com.bgl.bike.simulation.model.Grid;
import com.bgl.bike.simulation.model.Point;
import com.bgl.bike.simulation.model.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bgl.bike.simulation.util.Constants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandProcessor {

    @Value("${grid.rows}")
    private int rowCount;

    @Value("${grid.cols}")
    private int colCount;

    private final GridOperationExecutor gridOperationExecutor;

    private static Map<Command, GridOperation> commandMap = new HashMap<>();

    public void processCommand(List<String> commands) {
        int startIndex = findPlaceCommand(commands);
        String[] placeCommand = commands.get(startIndex).split(COMMAND_SEPARATOR_REGEX);
        int x = Integer.parseInt(placeCommand[X_COORDINATE_INDEX]);
        int y = Integer.parseInt(placeCommand[Y_COORDINATE_INDEX]);
        Direction direction = Direction.valueOf(placeCommand[DIRECTION_INDEX]);
        Grid grid = Grid.builder().rows(rowCount).cols(colCount)
                .startPosition(Position.builder().currentLocation(Point.builder().x(x).y(y).build()).direction(direction).build())
                .build();
        gridOperationExecutor.executeOperation(PlaceGridOperation.builder().grid(grid).build());
        populateCommandMap(grid);
        // Start after the Place command
        commands.stream().skip(startIndex + 1).map(Command::valueOf).forEach(cmd -> {
            commandMap.get(cmd).execute();
        });


    }

    private int findPlaceCommand(List<String> commands) {
        Integer startingIndex = commands.stream().filter(command -> command.startsWith(Command.PLACE.getValue()))
                .findFirst().map(value -> commands.indexOf(value)).orElse(INVALID_INDEX);

        if (startingIndex == INVALID_INDEX) {
            log.error("No place command found in the input commands");
            throw new InvalidInputException("Invalid input commands with no PLACE command");
        }
        return startingIndex;

    }

    private void populateCommandMap(Grid grid) {
        commandMap.put(Command.FORWARD, ForwardGridOperation.builder().grid(grid).build());
        commandMap.put(Command.TURN_LEFT, TurnLeftGridOperation.builder().grid(grid).build());
        commandMap.put(Command.TURN_RIGHT, TurnRightGridOperation.builder().grid(grid).build());
        commandMap.put(Command.GPS_REPORT, PrintGridOperation.builder().grid(grid).build());
    }
}
