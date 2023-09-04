package com.bgl.bike.simulation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class CommandProcessorTest {

    private CommandProcessor commandProcessor;

    @Spy
    private GridOperationExecutor gridOperationExecutor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        commandProcessor = new CommandProcessor(gridOperationExecutor);
        ReflectionTestUtils.setField(commandProcessor, "rowCount", 7);
        ReflectionTestUtils.setField(commandProcessor, "colCount", 7);
    }

    @Test
    public void testProcessCommands() {
        commandProcessor.processCommand(Arrays.asList("PLACE 1,2,EAST", "FORWARD", "FORWARD", "GPS_REPORT"));
        verify(gridOperationExecutor, times(4)).executeOperation(any());
    }

}