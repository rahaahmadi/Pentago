/**
 * Coordinate class shows position of a disc
 */
public class Coordinate {
    private int x;
    private int y;

    /**
     * Create a new coordinate with a given x , y
     * @param x x of coordinate
     * @param y y of coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get x of coordinate
     * @return x
     */
    public int getX() {
        if (x >= 3)
            return x - 3;
        else
            return x;
    }

    /**
     * get y of coordinate
     * @return y
     */
    public int getY() {
        if (y >= 3)
            return y-3;
        else
            return y;
    }

    /**
     * check if coordinate is in a valid range
     * @return true or false
     */
    public boolean validCoordinate() {
        return (x >= 0 && x < 6) && (y >= 0 && y < 6);
    }

    /**
     * find block number form coordinate
     * @return block number
     */
    public int findBlock() {
        if (x < 3 && y < 3)
            return 1;
        else if (x < 3 && y >= 3) {
            return 3;
        }

        else if (x >= 3 && y < 3) {
            return 2;
        }

        else {
            return 4;
        }
    }
}
