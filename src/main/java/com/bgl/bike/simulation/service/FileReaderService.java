package com.bgl.bike.simulation.service;

import java.util.List;

@FunctionalInterface
public interface FileReaderService {

    List<String> readFile(String filePath);
}
