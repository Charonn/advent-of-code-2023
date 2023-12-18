package main.java.challange;

import main.java.Day;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day02 extends Day {
    private final Pattern cubeMatcher = Pattern.compile("\\d+ (green|red|blue)");

    public Day02(String name, Path filePath) {
        super(name, filePath);
    }

    public Object part1() {
        var lines = this.getAllLinesString();
        return Arrays.stream(lines.split("\n"))
                .mapToInt(line -> {
                    var isExceeding = cubeMatcher.matcher(line)
                            .results()
                            .map(MatchResult::group)
                            .anyMatch(group -> {
                                int amount = Integer.parseInt(group.split(" ")[0]);
                                String cube = group.split(" ")[1];
                                return (Objects.equals(cube, "red") && amount > 12) ||
                                        (Objects.equals(cube, "green") && amount > 13) ||
                                        (Objects.equals(cube, "blue") && amount > 14);
                            });
                    return isExceeding ? 0 : Integer.parseInt(line.substring(5, line.indexOf(':')));
                }).sum();
    }

    public Object part2() {
        var lines = this.getAllLinesString();
        return Arrays.stream(lines.split("\n"))
                .mapToInt(line -> Pattern.compile("\\d+ [rgb]").matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .map(group -> Map.entry(
                                group.charAt(group.length() - 1),
                                Integer.parseInt(group.split(" ")[0])
                        ))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::max))
                        .values().stream()
                        .reduce(1, (a, b) -> a * b)).sum();
    }
}
