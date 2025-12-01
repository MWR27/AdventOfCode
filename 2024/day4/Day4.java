package aoc2024.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import aoc2024.*;

public class Day4 {

    public static void main(String[] args) throws IOException {
        first();
        second();
    }

    public static void first() throws IOException {
        String xmas = "XMAS";
        List<String> lines = Files.readAllLines(Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day4\\input"));
        int total = 0;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                total += searchForMatch(lines, xmas, j, i, 1, 0)
                       + searchForMatch(lines, xmas, j, i, 1, 1)
                       + searchForMatch(lines, xmas, j, i, 0, 1)
                       + searchForMatch(lines, xmas, j, i, -1, 1)
                       + searchForMatch(lines, xmas, j, i, -1, 0)
                       + searchForMatch(lines, xmas, j, i, -1, -1)
                       + searchForMatch(lines, xmas, j, i, 0, -1)
                       + searchForMatch(lines, xmas, j, i, 1, -1);
            }
        }
        System.out.println(total);
    }
    
    public static void second() throws IOException {
        String mas = "MAS";
        List<String> lines = Files.readAllLines(Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day4\\input"));
        int total = 0;

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 1; j < line.length(); j++) {
                if (line.charAt(j) == 'A'
                    && searchForMatch(lines, mas, j - 1, i - 1, 1, 1) + searchForMatch(lines, mas, j + 1, i + 1, -1, -1) == 1
                    && searchForMatch(lines, mas, j - 1, i + 1, 1, -1) + searchForMatch(lines, mas, j + 1, i - 1, -1, 1) == 1) {
                    total++;
                }
            }
        }
        System.out.println(total);
    }
    
    private static int searchForMatch(List<String> lines, String match, int x, int y, int delX, int delY) {
        for (int i = 0; i < match.length(); i++) {
            if (y < 0 || y >= lines.size() || x < 0 || x >= lines.get(y).length()) {
                return 0;
            } else if (match.charAt(i) == lines.get(y).charAt(x)) {
                x += delX;
                y += delY;
            } else {
                return 0;
            }
        }
        return 1;
    }
}
