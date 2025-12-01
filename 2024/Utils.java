package aoc2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Utils {
    public static BufferedReader getFileReader(String fileName) throws FileNotFoundException {
        return new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\" + fileName));
    }

    public static char[][] buildMap(String mapString) {
        String[] rows = mapString.split("\\s+");
        char[][] map = new char[rows.length][rows[0].length()];

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                map[row][col] = rows[row].charAt(col);
            }
        }

        return map;
    }
    
    public static boolean inMap(Coord coord, char[][] map) {
        return coord.x >= 0 && coord.x < map[0].length && coord.y >= 0 && coord.y < map.length;
    }
    
    public static boolean inMap(int x, int y, char[][] map) {
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length;
    }
}
