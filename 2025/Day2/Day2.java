import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Day2 {
    public static void main(String[] args) {
        puzzle1();
        puzzle2();
    }

    public static void puzzle1() {
        Path path = Paths.get(System.getProperty("user.dir") + "\\input");
        long total = 0;
        try (Scanner scanner = new Scanner(path)) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String[] bounds = scanner.next().split("-");
                long upperBound = Long.parseLong(bounds[1].trim());
                long sub = -1;
                long power = -1;
                if (bounds[0].length() % 2 == 1) {
                    sub = powerOf10(bounds[0].length() / 2);
                    power = sub * 10;
                } else {
                    power = powerOf10(bounds[0].length() / 2);
                    long sub1 = Long.parseLong(bounds[0].substring(0, bounds[0].length() / 2));
                    long sub2 = Long.parseLong(bounds[0].substring(bounds[0].length() / 2));
                    if (sub1 >= sub2) {
                        sub = sub1;
                    } else {
                        sub = sub1 + 1;
                    }
                }
                long num = sub * power + sub;
                while (num <= upperBound) {
                    total += num;
                    sub++;
                    if (sub == power) {
                        power *= 10;
                    }
                    num = sub * power + sub;                    
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        System.out.println("Puzzle 1 answer: " + total);
    }

    public static void puzzle2() {
        Path path = Paths.get(System.getProperty("user.dir") + "\\input");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
        
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private static long powerOf10(int n) {
        long[] powers = {1, 10, 100, 1000, 10000, 100000, 1000000};
        if (n < powers.length) {
            return powers[n];
        } else {
            long p = powers[powers.length - 1];
            n -= powers.length - 1;
            while (n > 0) {
                int exp = Math.min(powers.length - 1, n);
                p *= powers[exp];
                n -= exp;
            }
            return p;
        }
    }
}