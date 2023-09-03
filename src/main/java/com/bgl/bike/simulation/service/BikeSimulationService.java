package com.bgl.bike.simulation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BikeSimulationService {

    @Value("${input.file.name}")
    private String fileName;

    private final ResourceLoader resourceLoader;

    private final FileReaderService fileReaderService;

    private final CommandProcessor commandProcessor;

    public void start() {

        Resource resource = resourceLoader.getResource(String.format("classpath:%s", fileName));
        try {
            List<String> commands = fileReaderService.readFile(resource.getFile().getAbsolutePath());

            commandProcessor.processCommand(commands);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
