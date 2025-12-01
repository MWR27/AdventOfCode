package aoc2024.day6;

import aoc2024.*;

public class Guard {
    private char[][] map;
    private Coord loc;
    private Direction direction;
    
    public Guard(char[][] map, Coord loc) {
        this.map = map;
        this.loc = loc;
        this.direction = Direction.NORTH;
    }
    
    public Coord getLoc() {
        return loc.clone();
    }
    
    public boolean isFacing(Direction d) {
        return direction == d;
    }
    
    public boolean inMap() {
        return Utils.inMap(loc, map);
    }
    
    public Coord coordInFront() {
        return new Coord(loc.x + direction.x, loc.y + direction.y);
    }
    
    public char spaceInFront() {
        Coord nextCoord = this.coordInFront();
        if (Utils.inMap(nextCoord, map)) {
            return map[nextCoord.y][nextCoord.x];
        } else {
            return '\0';
        }
    }
    
    public void moveForward() {
        if (this.spaceInFront() != '#') {
            loc.x += direction.x;
            loc.y += direction.y;
        }
    }
    
    public void turnRight() {
        direction = direction.nextCardinalDirection();
        /*
        int oldX = direction.x;
        direction.x = -direction.y;
        direction.y = oldX;
        */
    }
}
