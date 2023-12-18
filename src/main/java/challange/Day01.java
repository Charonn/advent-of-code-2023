package main.java.challange;

import main.java.Day;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day01 extends Day {
    public static List<String> numbers = new LinkedList<>(Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine"));

    public Day01(String name, Path filePath) {
        super(name, filePath);
    }

    public Object part1() {
        var lines = this.getAllLinesString();
        return Arrays.stream(lines.split("\n"))
                .map(l -> l.replaceAll("\\D", ""))
                .map(l -> "" + l.charAt(0) + l.charAt(l.length() - 1))
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public Object part2() {
        var lines = this.getAllLinesString();
        return Arrays.stream(lines.split("\n"))
                .map(l -> {
                    for (int i = 0; i < numbers.size(); i++) {
                        l = l.replaceAll(numbers.get(i), numbers.get(i) + String.valueOf((i + 1)) + numbers.get(i));
                    }
                    return l.replaceAll("\\D", "");
                })
                .map(l -> "" + l.charAt(0) + l.charAt(l.length() - 1))
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
