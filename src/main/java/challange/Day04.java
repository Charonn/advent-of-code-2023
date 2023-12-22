package main.java.challange;

import main.java.Day;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
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
        return Arrays.stream(lines)
                .map(Card::create)
                .collect(Collectors.toMap(card -> card.game, CardSet::new))
                .values().stream()
                .map(set -> set.card)
                .map(Card::getWinningNumbers)
                .map(winning -> (int) Math.pow(2, winning.size()-1))
                .reduce(0, Integer::sum);
    }

    public Object part2() {
        var lines = this.getAllLinesString().split("\n");
        var cards = Arrays.stream(lines).map(Card::create).collect(Collectors.toMap(card -> card.game, CardSet::new));
        for (Map.Entry<Integer, CardSet> entry : cards.entrySet()) {
            CardSet set = entry.getValue();
            Set<Integer> winningNumbers = set.card.getWinningNumbers();

            for (int i = set.card.game + 1; i < set.card.game + 1 + winningNumbers.size(); i++) {
                for (int j = 0; j < set.count; j++) {
                    cards.get(i).incrementCount();
                }
            }
        }
        return cards.values().stream().map(set -> set.count).reduce(0, Integer::sum);
    }


    record Card(int game, Set<Integer> winning, Set<Integer> owned) {
        static Card create(String line) {
            var split = line.split(":");
            var items = split[1].trim().split("\\|");
            var n = Integer.parseInt(split[0].replaceAll("\s+", "\s").split(" ")[1]);
            var winning = Arrays.stream(items[0].trim().replaceAll("\s+", "\s").split(" ")).map(Integer::parseInt).collect(Collectors.toSet());
            var owned = Arrays.stream(items[1].trim().replaceAll("\s+", "\s").split(" ")).map(Integer::parseInt).collect(Collectors.toSet());
            return new Card(n, winning, owned);
        }

        public Set<Integer> getWinningNumbers() {
            Set<Integer> intersection = new HashSet<>(owned);
            intersection.retainAll(winning);
            return intersection;
        }
    }

    private static final class CardSet {
        private final Card card;
        private int count;

        public CardSet(Card card) {
            this.card = card;
            count = 1;
        }

        public void incrementCount() {
            count++;
        }
    }
}
