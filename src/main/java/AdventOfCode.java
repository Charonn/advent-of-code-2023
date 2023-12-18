package main.java;

import main.java.challange.Day01;
import main.java.challange.Day02;
import main.java.challange.Day03;
import main.java.challange.Day04;
import main.java.challange.Day11;

import java.nio.file.Paths;
import java.util.logging.Logger;

public class AdventOfCode {
    public static final Logger LOGGER = Logger.getLogger(AdventOfCode.class.getName());

    public static void main(String[] args) {
        DayManager manager = new DayManager()
                .addDay(new Day01("Day01", Paths.get("src/main/resources/day01.txt")))
                .addDay(new Day02("Day02", Paths.get("src/main/resources/day02.txt")))
                .addDay(new Day03("Day03", Paths.get("src/main/resources/day03.txt")))
                .addDay(new Day04("Day04", Paths.get("src/main/resources/day04.txt")))
                .addDay(new Day11("Day11", Paths.get("src/main/resources/day11.txt")));
        manager.run();
    }

}
