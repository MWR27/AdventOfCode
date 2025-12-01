package aoc2024.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import aoc2024.*;

public class Day6 {

    public static void main(String[] args) throws IOException {
        String string = Files.readString(Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day6\\input"));
        char[][] map = Utils.buildMap(string);
        
        first(map);
        second(map);
    }
    
    public static void first(char[][] map) throws IOException {
        Guard guard = new Guard(map, findGuard(map));
        Set<Coord> locs = new HashSet<>();
        
        
        while (guard.inMap()) {
            locs.add(guard.getLoc());
            if (guard.spaceInFront() == '#') {
                guard.turnRight();
            }
            guard.moveForward();
            
        }
        System.out.println(locs.size());
    }
    
    public static void second(char[][] map) throws IOException {
        Guard guard = new Guard(map, findGuard(map));
        Set<Coord> newObstructions = new HashSet<>();
        // queue
        Deque<Coord> obstructionLocs = new ArrayDeque<>();
        
        while (guard.inMap()) {
            if (guard.spaceInFront() == '#') {
                obstructionLocs.addLast(guard.coordInFront());
                guard.turnRight();
            }
            if (obstructionLocs.size() == 3) {
                Coord obstructionLoc = obstructionLocs.removeFirst();
                
                if (guard.isFacing(Direction.EAST) || guard.isFacing(Direction.WEST)) {
                    Coord newObstructionLoc = new Coord(obstructionLoc.x + (guard.isFacing(Direction.EAST) ? 1 : -1), guard.getLoc().y);
                    if (Utils.inMap(newObstructionLoc, map)) {
                        newObstructions.add(newObstructionLoc);
                    }
                } else if (guard.isFacing(Direction.NORTH) || guard.isFacing(Direction.SOUTH)) {
                    Coord newObstructionLoc = new Coord(guard.getLoc().x, obstructionLoc.y + (guard.isFacing(Direction.NORTH) ? 1 : -1));
                    if (Utils.inMap(newObstructionLoc, map)) {
                        newObstructions.add(newObstructionLoc);
                    }
                }
            }
            guard.moveForward();
            
        }
        System.out.println(newObstructions.size());
    }

    private static Coord findGuard(char[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                
                if (map[row][col] == '^') {
                    return new Coord(col, row);
                }
            }
        }
        return null;
    }

}
