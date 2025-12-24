import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.IOException;
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
        long total = 0;
        try (Scanner scanner = new Scanner(path)) {
            scanner.useDelimiter(",");
            Set<Long> ids = new HashSet<>();
            while (scanner.hasNext()) {
                String[] bounds = scanner.next().split("-");
                bounds[0] = bounds[0].trim();
                bounds[1] = bounds[1].trim();

                // Sum up IDs for each possible # of repetitions
                for (int nReps = 2; nReps <= bounds[1].length(); nReps++) {
                    total += sumInvalidIDs(bounds[0], Long.parseLong(bounds[1]), nReps, ids);
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        System.out.println("Puzzle 2 answer: " + total);
    }

    private static long sumInvalidIDs(String lower, long upper, int nReps, Set<Long> ids) {
        long total = 0;
        long sub = -1;
        long power = -1;
        int subLength = -1;

        // Find substring to repeat
        if (lower.length() % nReps == 0) {
            subLength = lower.length() / nReps;
            power = powerOf10(subLength);
            sub = Long.parseLong(lower.substring(0, subLength));
            for (int i = 1; i <= nReps - 1; i++) {
                long subN = Long.parseLong(lower.substring(subLength * i, subLength * (i + 1)));
                if (sub < subN) {
                    sub++;
                }
                if (sub != subN) {
                    break;
                }
            }
        } else {
            subLength = lower.length() / nReps + 1;
            sub = powerOf10(lower.length() / nReps);
            power = sub * 10;
        }
        long num = sub;
        long addend = 1;
        // Construct starting number num
        // addend is the number that effectively increases each repeated substring by 1
        for (int i = 1; i < nReps; i++) {
            num = num * power + sub;
            addend = addend * power + 1;
        }
        // Need to check to find when each repeated substring hits a power of 10
        long max = powerOf10(subLength * nReps);
        while (num <= upper) {
            if (ids.add(num)) {
                total += num;
            }
            if (num < max - 1) {
                num += addend;
            } else {
                sub = power;
                power *= 10;
                max *= powerOf10(nReps);
                num = sub;
                addend = 1;
                // construct new starting num and addend
                for (int i = 1; i < nReps; i++) {
                    num = num * power + sub;
                    addend = addend * power + 1;
                }
            }
        }

        return total;
    }

    // Finds nth power of 10
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