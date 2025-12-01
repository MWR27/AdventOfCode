package aoc2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Day1 {

	public static void main(String[] args) throws IOException {
		first();
		second();
	}

	public static void first() throws FileNotFoundException, IOException {
		try (BufferedReader in = get("input")) {
			String l;
			String[] arr = new String[2];
			PriorityQueue<Integer> one = new PriorityQueue<>();
			PriorityQueue<Integer> two = new PriorityQueue<>();
			while ((l = in.readLine()) != null) {
				arr = l.split("\\s+");
				one.add(Integer.parseInt(arr[0]));
				two.add(Integer.parseInt(arr[1]));
			}
			
			int sum = 0;
			while (!one.isEmpty()) {
				sum += Math.abs(one.remove() - two.remove());
			}
			
			System.out.println(sum);
		}
	}
	
	public static void second() throws FileNotFoundException, IOException {
		try (BufferedReader in = get("input")) {
			String l;
			String[] arr = new String[2];
			ArrayList<Integer> left = new ArrayList<>();
			ArrayList<Integer> right = new ArrayList<>();
			HashMap<Integer, Integer> map = new HashMap<>();

			while ((l = in.readLine()) != null) {
				arr = l.split("\\s+");
				left.add(Integer.parseInt(arr[0]));
				right.add(Integer.parseInt(arr[1]));
			}

			int sim = 0;
			for (int numLeft : left) {
				if (map.containsKey(numLeft)) {
					sim += map.get(numLeft);
				} else {
					int count = 0;
					for (int numRight : right) {
						if (numLeft == numRight) {
							count++;
						}
					}
					sim += numLeft * count;
					map.put(numLeft, numLeft * count);
				}
			}
			
			System.out.println(sim);
		}
	}
	
	public static BufferedReader get(String fileName) throws FileNotFoundException {
		return new BufferedReader(new FileReader("C:\\Users\\MWR\\Desktop\\Projects\\Advent of Code\\aoc2024\\src\\aoc2024\\" + fileName));
	}

}
