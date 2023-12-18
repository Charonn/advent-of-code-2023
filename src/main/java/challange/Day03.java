package main.java.challange;

import main.java.Day;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Day03 extends Day {

    public Day03(String name, Path filePath) {
        super(name, filePath);
    }

    public Object part1() {
        var lines = this.getAllLinesString().split("\n");
        var numbers = new HashSet<Number>();
        for (int i = 0; i < lines.length; i++) {
            var matcher = Pattern.compile("\\d+").matcher(lines[i]);
            while (matcher.find()) {
                numbers.add(new Number(matcher.start(), i, Integer.parseInt(matcher.group())));
            }
        }
        var symbols = new HashSet<Symbol>();
        for (int i = 0; i < lines.length; i++) {
            var matcher = Pattern.compile("[^0-9.]").matcher(lines[i]);
            while (matcher.find()) {
                symbols.add(new Symbol(matcher.start(), i, matcher.group().charAt(0)));
            }
        }
        return numbers.stream()
                .mapToInt(n -> {
                    var adjecent = false;
                    for (var s : symbols)
                        for (int i = 0; i < (n.value + "").length(); i++)
                            if (isAdjacent(s.x, s.y, n.x + i, n.y))
                                adjecent = true;
                    return adjecent ? n.value : 0;
                })
                .sum();
    }

    public Object part2() {
        var lines = this.getAllLinesString().split("\n");
        var numbers = new HashSet<Number>();
        for (int i = 0; i < lines.length; i++) {
            var matcher = Pattern.compile("\\d+").matcher(lines[i]);
            while (matcher.find()) {
                numbers.add(new Number(matcher.start(), i, Integer.parseInt(matcher.group())));
            }
        }
        var symbols = new HashSet<Symbol>();
        for (int i = 0; i < lines.length; i++) {
            var matcher = Pattern.compile("[*\\-#/=%$&@+]").matcher(lines[i]);
            while (matcher.find()) {
                symbols.add(new Symbol(matcher.start(), i, matcher.group().charAt(0)));
            }
        }
        return symbols.stream()
                .filter(s -> s.value == '*')
                .mapToInt(s -> {
                    var r = numbers.stream()
                            .map(n -> {
                                for (int i = 0; i < (n.value + "").length(); i++)
                                    if (isAdjacent(s.x, s.y, n.x + i, n.y))
                                        return n.value;
                                return -1;
                            })
                            .filter(v -> v != -1)
                            .toList();
                    return r.size() > 1 ? r.get(0) * r.get(1) : 0;
                })
                .sum();
    }

    static boolean isAdjacent(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;
    }

    record Number(int x, int y, int value) {
    }

    record Symbol(int x, int y, char value) {
    }
}
