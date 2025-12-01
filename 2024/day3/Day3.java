package aoc2024.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.*;

public class Day3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		first();
		second();
	}
	
	public static void first() throws IOException {
		Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
		String in = Files.readString(Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day3\\input"));
		Matcher matcher = pattern.matcher(in);
		
		int total = 0;
		
		while (matcher.find()) {
			total += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
		}
		System.out.println(total);
	}

	public static void second() throws IOException {
		Pattern enablePattern = Pattern.compile("(do|don't)\\(\\)");
		String in = Files.readString(Path.of("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\day3\\input"));
		Matcher enableMatcher = enablePattern.matcher(in);
		
		int total = 0;
		int start = 0;
		boolean enabled = true;
		
		while (enableMatcher.find()) {
			if (enableMatcher.group(1).equals("do") && !enabled) {
				start = enableMatcher.end();
				enabled = true;
			} else if (enableMatcher.group(1).equals("don't") && enabled) {
				total += totalMuls(in.substring(start, enableMatcher.start()));
				enabled = false;
			}
		}
		
		if (enabled) {
			total += totalMuls(in.substring(start));
		}
		
		System.out.println(total);
		
	}
	
	private static int totalMuls(String s) {
		Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
		Matcher matcher = pattern.matcher(s);
		
		int total = 0;
		
		while (matcher.find()) {
			total += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
		}
		return total;
	}
}
