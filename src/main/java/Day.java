package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public abstract class Day {

    private final Path filePath;
    protected String name;

    protected Day(String name, Path filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public List<String> getAllLinesStringList() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(this.filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public String getAllLinesString() {
        String lines = null;
        try {
            lines = Files.readString(this.filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public List<Integer> getAllLinesInteger() {
        List<Integer> lines = null;
        try {
            lines = Files.readAllLines(this.filePath).stream().map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    protected void start() {
        AdventOfCode.LOGGER.log(Level.INFO, String.format("%s Solution Part1: %s", this.name, part1()));
        AdventOfCode.LOGGER.log(Level.INFO, String.format("%s Solution Part2: %s", this.name, part2()));
    }

    public abstract Object part1();

    public abstract Object part2();

}
