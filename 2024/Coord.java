package aoc2024;

public class Coord implements Cloneable {
	public int x;
	public int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Coord other) {
			return this.x == other.x && this.y == other.y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
	    return x * 31 + y;
	}
	
	@Override
	public String toString() {
	    return String.format("(%d, %d)", x, y);
	}
	
	public Coord clone() {
	    return new Coord(x, y);
	}
}
