package main.java.challange;

import main.java.Day;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 extends Day {

    private final Pattern numberPattern = Pattern.compile(".*: ([\\d ]+) | ([\\d ]+)");

    public Day04(String name, Path filePath) {
        super(name, filePath);
    }

    private static Set<Integer> parseIntegerSet(String input) {
        return Arrays.stream(input.trim().split(" ")).map(value -> Integer.parseInt(value.replace(" ", ""))).collect(Collectors.toSet());
    }

    public Object part1() {
        var lines = this.getAllLinesString().split("\n");
        var cards = new ArrayList<Card>();
        for (int i = 0; i < lines.length; i++) {
            var parts = lines[i].split(":")[1].replaceAll(" +", " ").split("\\|");
            cards.add(new Card(i + 1, parseIntegerSet(parts[0]), parseIntegerSet(parts[1])));
        }
        return cards.stream()
                .mapToInt(card -> {
                    var m = card.amountMatching();
                    return (int) (m > 0 ? Math.pow(2, m) : 0) / 2;
                }).sum();
    }

    public Object part2() {
        var lines = this.getAllLinesString();
        return "";
    }

    record Card(int game, Set<Integer> winning, Set<Integer> owned) {
        public int amountMatching() {
            return (int) owned.stream().filter(winning::contains).count();
        }
    }
}
