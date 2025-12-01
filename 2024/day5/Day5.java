package aoc2024.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.*;

public class Day5 {

    public static void main(String[] args) throws IOException {
        first();

    }
    
    public static void first() throws IOException {
        String string = Files.readString(Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day5\\input"));
        String[] sections = string.split("\\n\\n");
        
        Map<String, Set<String>> rules = buildRules(sections[0]);
        
        Scanner sc = new Scanner(sections[1]);
        int total = 0;
        
        while (sc.hasNextLine()) {
             String line = sc.nextLine();
             String[] pageNumbers = line.split(",");
             
             if (isCorrectlyOrdered(pageNumbers, rules)) {
                 total += Integer.parseInt(pageNumbers[pageNumbers.length / 2]);
             }
        }
        
        System.out.println(total);
    }
    
    public static void second() throws IOException {
        String string = Files.readString(Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day5\\input"));
        String[] sections = string.split("\\n\\n");
        
        Map<String, Set<String>> rules = buildRules(sections[0]);
        
        Scanner sc = new Scanner(sections[1]);
        int total = 0;
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] pageNumbers = line.split(",");
            
            boolean isValid = true;
            for (int i = pageNumbers.length - 1; i >= 0; i--) {
                if (rules.containsKey(pageNumbers[i])) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (rules.get(pageNumbers[i]).contains(pageNumbers[j])) {
                            isValid = false;
                            swap(pageNumbers, i, j);
                        }
                    }
                }
            }
            
            if (!isValid) {
                total += Integer.parseInt(pageNumbers[pageNumbers.length / 2]);
            }
        }
        System.out.println(total);
    }
    
    private static Map<String, Set<String>> buildRules(String s) {
        Pattern orderingPattern = Pattern.compile("(\\d+)\\|(\\d+)");
        Matcher ruleMatcher = orderingPattern.matcher(s);
        
        Map<String, Set<String>> rules = new HashMap<>();
        
        while (ruleMatcher.find()) {
            String x = ruleMatcher.group(1);
            String y = ruleMatcher.group(2);
            if (rules.containsKey(x)) {
                rules.get(x).add(y);
            } else {
                HashSet<String> set = new HashSet<>();
                set.add(y);
                rules.put(x, set);
            }
        }
        
        return rules;
    }
    
    private static boolean isCorrectlyOrdered(String[] pageNumbers, Map<String, Set<String>> rules) {
        boolean isValid = true;
        for (int i = pageNumbers.length - 1; i >= 0 && isValid; i--) {
            if (rules.containsKey(pageNumbers[i])) {
                for (int j = i - 1; j >= 0; j--) {
                    if (rules.get(pageNumbers[i]).contains(pageNumbers[j])) {
                        isValid = false;
                        break;
                    }
                }
            }
        }
        return isValid;
    }
    
    private static void swap(String[] arr, int a, int b) {
        String temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
