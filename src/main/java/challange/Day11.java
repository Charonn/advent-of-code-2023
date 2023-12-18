package main.java.challange;

import main.java.Day;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day11 extends Day {

    public Day11(String name, Path filePath) {
        super(name, filePath);
    }

    private static Set<Integer> findVoids(String[][] map) {
        var voids = new HashSet<Integer>();
        for (int i = 0; i < map.length; i++) {
            if (Arrays.stream(map[i]).noneMatch(l -> l.contains("#"))) {
                voids.add(i);
            }
        }
        return voids;
    }

    public Object part1() {
        var lines = this.getAllLinesString().split("\n");
        var map = getMatrix(lines);
        var mapT = transpose(map);
        Set<Integer> xVoids;
        Set<Integer> yVoids;

        xVoids = findVoids(map);
        yVoids = findVoids(mapT);

        var galaxies = new ArrayList<Galaxy>();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < mapT.length; y++) {
                if (Objects.equals(map[x][y], "#")) {
                    galaxies.add(new Galaxy(x, y, x + y));
                }
            }
        }
        var distances = new ArrayList<Integer>();
        for (int i = 0; i < galaxies.size(); i++) {
            var g1 = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                var g2 = galaxies.get(j);
                var distance = Math.abs(g1.x - g2.x) + Math.abs(g1.y - g2.y);
                var xDistanceAdd = xVoids.stream().filter(v -> v >= Math.min(g1.x, g2.x) && v <= Math.max(g1.x, g2.x)).count();
                var yDistanceAdd = yVoids.stream().filter(v -> v >= Math.min(g1.y, g2.y) && v <= Math.max(g1.y, g2.y)).count();
                distance = (int) (distance + xDistanceAdd + yDistanceAdd);
                distances.add(distance);
            }
        }
        return distances.stream().mapToInt(i -> i.intValue()).sum();
    }

    public Object part2() {
        var lines = this.getAllLinesString();
        return "";
    }

    private String[][] getMatrix(String[] input) {
        int numberOfColumns = input[0].split("").length;
        int numberOfRows = input.length;
        String[][] matrix = new String[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            String[] splitInput = input[i].split("");
            matrix[i] = splitInput;
        }
        return matrix;
    }

    private String[][] transpose(String[][] array) {
        int width = array.length;
        int height = array[0].length;

        String[][] array_new = new String[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                array_new[y][x] = array[x][y];
            }
        }
        return array_new;
    }

    record Galaxy(int x, int y, int name) {
    }
}
