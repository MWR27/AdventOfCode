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
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                int firstIndex = 0;
                int secondIndex = 1;
                for (int i = 0, j = 1; i < line.length() - 1 && j < line.length();) {
                    j++;
                    if (line.charAt(j) > line.charAt(secondIndex)) {
                        secondIndex = j;
                    }
                    i++;
                    if (line.charAt(i) > line.charAt(firstIndex)) {
                        firstIndex = i;
                    }
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static void puzzle2() {
        Path path = Paths.get(System.getProperty("user.dir") + "\\input");
        try (BufferedReader reader = Files.newBufferedReader(path)) {

        while ((line = reader.readLine()) != null) {
            
        }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}