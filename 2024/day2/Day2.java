package aoc2024.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		first();
		System.out.println(System.getProperty("user.dir"));
	}

	public static void first() throws FileNotFoundException, IOException {
		List<String> lines = Files.readAllLines(
				Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day2\\input"));
		int numSafeReports = 0;
		for (String line : lines) {
			Scanner sc = new Scanner(line);
			List<Integer> levels = new ArrayList<>();
			levels.add(sc.nextInt());
			levels.add(sc.nextInt());
			int diff = levels.getLast() - levels.getFirst();
			if (Math.abs(diff) >= 1 && Math.abs(diff) <= 3) {
				boolean safe = true;
				while (sc.hasNextInt()) {
					int level = sc.nextInt();
					int newDiff = level - levels.getLast();
					if (Math.abs(newDiff) >= 1 && Math.abs(newDiff) <= 3 && Math.signum(diff) == Math.signum(newDiff)) {
						diff = newDiff;
						levels.add(level);
					} else {
						safe = false;
						break;
					}
				}
				if (safe) {
					numSafeReports++;
				}
			}

		}
		System.out.println(numSafeReports);
	}
	
	public static void second() throws FileNotFoundException, IOException {
		List<String> lines = Files.readAllLines(
				Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day2\\input"));
		int numSafeReports = 0;
		for (String line : lines) {
			Scanner sc = new Scanner(line);
			//int level = 
		}
	}


	public static BufferedReader get(String fileName) throws FileNotFoundException {
		return new BufferedReader(new FileReader("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day2\\" + fileName));
	}

}
