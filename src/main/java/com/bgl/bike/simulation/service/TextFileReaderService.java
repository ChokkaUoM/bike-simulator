package com.bgl.bike.simulation.service;

import com.bgl.bike.simulation.exceptions.InvalidFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class TextFileReaderService implements FileReaderService {
    @Override
    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            log.error("Exception occurred file reading file", e);
            throw new InvalidFileException("Invalid file");
        }
    }
}
