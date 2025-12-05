import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Day1 {
    public static void main(String[] args) {
        puzzle1();
        puzzle2();
    }

    public static void puzzle1() {
        Path path = Paths.get(System.getProperty("user.dir") + "\\input");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            int pos = 50;
            int total = 0;
        while ((line = reader.readLine()) != null) {
            int dir = line.charAt(0) == 'L' ? -1 : 1;
            int count = Integer.parseInt(line.substring(1));
            if ((pos = (pos + dir * count) % 100) == 0) {
                total++;
            }
        }
        System.out.println("Puzzle 1 Password: " + total);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static void puzzle2() {
        Path path = Paths.get(System.getProperty("user.dir") + "\\input");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            int pos = 50;
            int total = 0;
        while ((line = reader.readLine()) != null) {
            int dir = line.charAt(0) == 'L' ? -1 : 1;
            int count = Integer.parseInt(line.substring(1));
            total += count / 100;
            int calc = pos + dir * (count % 100);
            if (count % 100 != 0 && pos != 0 && (calc <= 0 || calc >= 100)) {
                total++;
            }
            pos = Math.floorMod(calc, 100);
        }
        System.out.println("Puzzle 2 Password: " + total);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}