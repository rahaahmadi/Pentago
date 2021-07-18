/**
 * This class represent each block of the board
 */
public class Block {
    // 3*3 array of discs
    private char[][] discs;

    /**
     * Create a new block and initialize it with 0
     */
    public Block () {
        discs = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                discs[i][j]='0';
    }

    /**
     * get discs array
     * @return 3*3 array
     */
    public char[][] getDiscs() {
        return discs;
    }

    /**
     * check if the position of coordinate is empty
     * @param coordinate move coordinate
     * @return true or false
     */
    public boolean validMove(Coordinate coordinate) {
        return discs[coordinate.getY()][coordinate.getX()] == '0';
    }

    /**
     * set a disc on a block
     * @param player game player
     * @param coordinate move coordinate
     */
    public void move(Player player, Coordinate coordinate) {
        if (player.getColor().equals("black"))
            discs[coordinate.getY()][coordinate.getX()] = 'B';
        else if (player.getColor().equals("red"))
            discs[coordinate.getY()][coordinate.getX()] = 'R';
    }

    /**
     * rotate block clockwise
     */
    public void rotateClockwise() {
        int n = discs.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                char temp = discs[i][j];
                discs[i][j] = discs[n - 1 - j][i];
                discs[n - 1 - j][i] = discs[n - 1 - i][n - 1 - j];
                discs[n - 1 - i][n - 1 - j] = discs[j][n - 1 - i];
                discs[j][n - 1 - i] = temp;
            }
        }
    }

    /**
     * rotate block anticlockwise
     */
    public void rotateAnticlockwise() {
        int n = discs.length;
        for (int x = 0; x < n / 2; x++) {
            for (int y = x; y < n - x - 1; y++) {
                char temp = discs[x][y];
                discs[x][y] = discs[y][n - 1 - x];
                discs[y][n - 1 - x] = discs[n - 1 - x][n - 1 - y];
                discs[n - 1 - x][n - 1 - y] = discs[n - 1 - y][x];
                discs[n - 1 - y][x] = temp;
            }
        }
    }

    /**
     * get discs array line by line
     * @param line line number
     * @return the string of that line
     */
    public String getLine(int line) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 3; j++)
                result.append(discs[line][j]).append(" ");
        return result.toString();
    }
}
