package main.java.challange;

import main.java.Day;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 extends Day {

    private final Point START = new Point(-1, 0);
    private final Direction START_DIRECTION = Direction.EAST;
    private String[][] map;

    public Day16(String name, Path filePath) {
        super(name, filePath);
    }

    public Object part1() {
        var lines = this.getAllLinesString().split("\n");
        this.map = this.getMatrix(lines);
        var energized = new Direction[lines[0].split("").length][lines.length];
        var lasers = new ArrayList<Laser>();
        lasers.add(new Laser(START, START_DIRECTION));
        while (true) {
            var removableLasers = lasers.stream()
                    .filter(l -> energized[l.next(map).y()][Math.max(0, l.next(map).x())] != null &&energized[l.next(map).y()][Math.max(0, l.next(map).x())] == l.direction())
                    .toList();
            lasers.removeAll(removableLasers);

            var newLasers = new ArrayList<Laser>();
            for (var laser : lasers) {
                newLasers.addAll(laser.move(map));
            }
            if (lasers.isEmpty()) {
                break;
            } else {
                lasers = newLasers;
            }
            for (var laser :lasers){
                energized[laser.start.y()][laser.start.x()] = laser.direction;
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if(energized[i][j] == null) {
//                        System.out.print(map[i][j]);
                    }else if(energized[i][j] != null && !map[i][j].equals(".")){
//                        System.out.print(map[i][j]);
                    }else{
                        switch (energized[i][j]){
//                            case EAST -> System.out.print(">");
//                            case WEST-> System.out.print("<");
//                            case NORTH -> System.out.print("^");
//                            case SOUTH -> System.out.print("v");
                        }
                    }
                }
//                System.out.println();
            }
//            System.out.println("\n---------------------------------------------------------------------------------------------------\n");
        }
        int energizedAmount = 0;
        for (int i = 0; i < energized.length; i++) {
            for (int j = 0; j < energized[0].length; j++) {
                if(energized[i][j] != null){
                    energizedAmount += 1;
                }
            }
        }
        return energizedAmount;
    }

    public Object part2() {
        var lines = this.getAllLinesString().split("\n");
        return "";
    }

    record Laser(Point start, Direction direction) {
        public Point next(String[][] map){
            return start.add(direction.dir, map[0].length, map.length);
        }

        public List<Laser> move(String[][] map) {
            var newPoint = next(map);
            if(newPoint.equals(this.start)){
                return new ArrayList<>();
            }
            var s = map[newPoint.x()][newPoint.y()];
            var lasers = new ArrayList<Laser>();
            switch (s) {
                case "|":
                    if (direction == Direction.EAST || direction == Direction.WEST) {
                        lasers.add(new Laser(newPoint, Direction.NORTH));
                        lasers.add(new Laser(newPoint, Direction.SOUTH));
                    } else {
                        lasers.add(new Laser(newPoint, this.direction()));
                    }
                    break;
                case "-":
                    if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                        lasers.add(new Laser(newPoint, Direction.WEST));
                        lasers.add(new Laser(newPoint, Direction.EAST));
                    } else {
                        lasers.add(new Laser(newPoint, this.direction()));
                    }
                    break;
                case "/":
                    if (direction == Direction.EAST) {
                        lasers.add(new Laser(newPoint, Direction.NORTH));
                    }
                    if (direction == Direction.WEST) {
                        lasers.add(new Laser(newPoint, Direction.SOUTH));
                    }
                    if (direction == Direction.NORTH) {
                        lasers.add(new Laser(newPoint, Direction.EAST));
                    }
                    if (direction == Direction.SOUTH) {
                        lasers.add(new Laser(newPoint, Direction.WEST));
                    }
                    break;
                case "\\":
                    if (direction == Direction.EAST) {
                        lasers.add(new Laser(newPoint, Direction.SOUTH));
                    }
                    if (direction == Direction.WEST) {
                        lasers.add(new Laser(newPoint, Direction.NORTH));
                    }
                    if (direction == Direction.NORTH) {
                        lasers.add(new Laser(newPoint, Direction.WEST));
                    }
                    if (direction == Direction.SOUTH) {
                        lasers.add(new Laser(newPoint, Direction.EAST));
                    }
                    break;
                case ".":
                default:
                    if (newPoint != start) {
                        lasers.add(new Laser(newPoint, this.direction()));
                    }
            }
            return lasers;
        }

    }

    record Point(int x, int y) {
        public Point add(Point other, int maxX, int maxY) {
            var x = Math.min(Math.max(0, this.x() + other.x), maxX -1);
            var y = Math.min(Math.max(0, this.y() + other.y), maxY -1);
            return new Point(x, y);
        }
    }

    enum Direction {
        NORTH(new Point(0, -1)),
        EAST(new Point(1, 0)),
        SOUTH(new Point(0, 1)),
        WEST(new Point(-1, 0));
        private final Point dir;

        private Direction(Point dir) {
            this.dir = dir;
        }

        public Point getDirection() {
            return dir;
        }
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
}
