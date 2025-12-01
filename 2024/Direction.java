package aoc2024;

public enum Direction {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);
    
    public final int x;
    public final int y;
    
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Direction opposite() {
        switch (this) {
            case NORTH: return SOUTH;
            case EAST: return WEST;
            case SOUTH: return NORTH;
            case WEST: return EAST;
            default: return null;
        }    
    }
    
    public Direction nextCardinalDirection() {
        switch (this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            default: return null;
        }  
    }
    
}
