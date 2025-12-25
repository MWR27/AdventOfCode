import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day4 {
    public static void main(String[] args) throws IOException {
        char[][] grid = to2DArray(Paths.get(args[0]));
        puzzle1(grid);
        puzzle2(grid);
    }

    public static void puzzle1(char[][] grid) {
        int total = 0;

        for (int row = 0; row < grid.length; row++) {
            int leftRollCount = 0;
            int middleRollCount = 0;
            int rightRollCount = isRoll(row - 1, 0, grid) + isRoll(row, 0, grid) + isRoll(row + 1, 0, grid);
            for (int col = 0; col < grid[0].length; col++) {
                leftRollCount = middleRollCount;
                middleRollCount = rightRollCount;
                rightRollCount = isRoll(row - 1, col + 1, grid) + isRoll(row, col + 1, grid) + isRoll(row + 1, col + 1, grid);
                if (grid[row][col] == '@' && leftRollCount + middleRollCount + rightRollCount - 1 < 4) {
                    total++;
                }
            }
        }

        System.out.println("Puzzle 1 answer: " + total);
    }

    public static void puzzle2(char[][] grid) {
        int total = 0;
        int nRemoved;
        while ((nRemoved = removeRolls(grid)) > 0) {
            total += nRemoved;
        }
        System.out.println("Puzzle 2 answer: " + total);
    }

    private static int isRoll(int row, int col, char[][] grid) {
        return (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == '@') ? 1 : 0;
    }

    private static int removeRolls(char[][] grid) {
        int total = 0;

        for (int row = 0; row < grid.length; row++) {
            int leftRollCount = 0;
            int middleRollCount = 0;
            int rightRollCount = isRoll(row - 1, 0, grid) + isRoll(row, 0, grid) + isRoll(row + 1, 0, grid);
            for (int col = 0; col < grid[0].length; col++) {
                leftRollCount = middleRollCount;
                middleRollCount = rightRollCount;
                rightRollCount = isRoll(row - 1, col + 1, grid) + isRoll(row, col + 1, grid) + isRoll(row + 1, col + 1, grid);
                if (grid[row][col] == '@' && leftRollCount + middleRollCount + rightRollCount - 1 < 4) {
                    grid[row][col] = 'x';
                    total++;
                }
            }
        }

        return total;
    }

    public static char[][] to2DArray(Path path) throws IOException {
        BufferedReader reader = Files.newBufferedReader(path);
        int nCols = reader.readLine().length();
        int nRows = 1;
        while (reader.readLine() != null) {
            nRows++;
        }
        char[][] arr = new char[nRows][nCols];
        reader.close();

        reader = Files.newBufferedReader(path);
        for (int row = 0; row < nRows; row++) {
            String line = reader.readLine();
            for (int col = 0; col < nCols; col++) {
                arr[row][col] = line.charAt(col);
            }
        }
        reader.close();
        return arr;
    }

    public static void print2DCharArray(char[][] arr) {
        int nRows = arr.length;
        int nCols = arr[0].length;
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                System.out.print(arr[row][col]);
            }
            System.out.println();
        }
    }
}
