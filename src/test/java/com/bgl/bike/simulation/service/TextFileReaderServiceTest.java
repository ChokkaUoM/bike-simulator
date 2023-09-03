package com.bgl.bike.simulation.service;

import com.bgl.bike.simulation.exceptions.InvalidFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextFileReaderServiceTest {

    private TextFileReaderService textFileReaderService;

    @BeforeEach
    public void setUp() {
        textFileReaderService =  new TextFileReaderService();
    }

    @Test
    void readInvalidFile() {
        String fileName = "invalidFile.txt";
        Assertions.assertThrows(InvalidFileException.class, () -> textFileReaderService.readFile(fileName));
    }
}