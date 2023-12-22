package main.java.challange;

import main.java.Day;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;

public class Day06 extends Day {

    public Day06(String name, Path filePath) {
        super(name, filePath);
    }


    public Object part1() {
        var times = Arrays.stream(this.getAllLinesString().split("\n")[0]
                        .split(": ")[1]
                        .trim()
                        .split(" "))
                .filter(s -> !s.isBlank())
                .map(Long::parseLong)
                .toList();

        var distances = Arrays.stream(this.getAllLinesString().split("\n")[1]
                        .split(": ")[1]
                        .trim()
                        .split(" "))
                .filter(s -> !s.isBlank())
                .map(Long::parseLong)
                .toList();
        var raceToPossibilities = new HashMap<Integer, Integer>();
        for (int race = 0; race < times.size(); race++) {
            int possibilities = 0;
            for (int i = 0; i < times.get(race); i++) {
                var distance = i * (times.get(race) - i);
                if (distance > distances.get(race)) {
                    possibilities++;
                }
            }
            raceToPossibilities.put(race, possibilities);
        }
        return raceToPossibilities.values().stream().reduce(1, (x, y) -> x * y);
    }

    public Object part2() {
        var time = Long.parseLong(this.getAllLinesString().split("\n")[0].split(": ")[1].trim().replaceAll(" ", ""));

        var distance = Long.parseLong(this.getAllLinesString().split("\n")[1].split(": ")[1].trim().replaceAll(" ", ""));
        int possibilities = 0;
        for (int i = 0; i < time; i++) {
            var d = i * (time - i);
            if (d > distance) {
                possibilities++;
            }
        }
        return possibilities;
    }
}
