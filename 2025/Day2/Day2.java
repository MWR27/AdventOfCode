import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
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
        BigInteger total = BigInteger.ZERO;
        try (Scanner scanner = new Scanner(path)) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String[] bounds = scanner.next().split("-");
                bounds[1] = bounds[1].trim();
                String sub = null;
                if (bounds[0].length() % 2 == 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append('1');
                    for (int i = 0; i < bounds[0].length() / 2; i++) {
                        sb.append('0');
                    }
                    sub = sb.toString();
                } else {
                    String sub1 = bounds[0].substring(0, bounds[0].length() / 2);
                    String sub2 = bounds[0].substring(bounds[0].length() / 2);
                    BigInteger sub1Int = new BigInteger(sub1);
                    BigInteger sub2Int = new BigInteger(sub2);
                    if (sub1Int.compareTo(sub2Int) >= 0) {
                        sub = sub1;
                    } else {
                        sub = (new BigInteger(sub1)).add(BigInteger.ONE).toString();
                    }
                }
                BigInteger end = new BigInteger(bounds[1]);
                BigInteger num = new BigInteger(sub + sub);
                while (num.compareTo(end) <= 0) {
                    System.out.println(num);
                    total = total.add(num);
                    sub = (new BigInteger(sub)).add(BigInteger.ONE).toString();
                    num = new BigInteger(sub + sub);
                    
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        System.out.println("Puzzle 1 Number of invalid IDs: " + total);
    }

    public static void puzzle2() {
        Path path = Paths.get(System.getProperty("user.dir") + "\\input");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
        
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}