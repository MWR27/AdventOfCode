import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Day3 {
    public static void main(String[] args) {
        puzzle1();
        puzzle2();
    }

    public static void puzzle1() {
        Path path = Paths.get(System.getProperty("user.dir") + "\\input");
        int totalJoltage = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                int firstIndex = 0;
                int secondIndex = 1;
                for (int i = 2; i < line.length(); i++) {
                    if (line.charAt(secondIndex) > line.charAt(firstIndex)) {
                        firstIndex = secondIndex;
                        secondIndex = i;
                    } else if (line.charAt(i) > line.charAt(secondIndex)) {
                        secondIndex = i;
                    }
                }
                int joltage = (line.charAt(firstIndex) - '0') * 10 + (line.charAt(secondIndex) - '0');
                System.out.println(joltage);
                totalJoltage += joltage;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        System.out.println("Puzzle 1 total output joltage: " + totalJoltage);
    }

    public static void puzzle2() {
        Path path = Paths.get(System.getProperty("user.dir") + "\\input");
        int totalJoltage = 0;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                int[] digitIndices = new int[12];
                for (int i = 0; i < 12; i++) {
                    
                }
                for (int i = 12; i < line.length(); i++) {
                    if (line.charAt(startIndex + 1) > line.charAt(startIndex)) {
                        startIndex++;
                        endIndex++;
                    } else if (line.charAt(i) > line.charAt(endIndex)) {
                        endIndex = i;
                    }
                }
                int joltage = (line.charAt(startIndex) - '0') * 10 + (line.charAt(endIndex) - '0');
                System.out.println(joltage);
                totalJoltage += joltage;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        System.out.println("Puzzle 2 total output joltage: " + totalJoltage);
    }

    //private static int largestDigitIndex(String digits) 
}