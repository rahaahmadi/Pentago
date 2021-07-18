/**
 * The game is played on a 6Ãƒâ€”6 board divided into four 3Ãƒâ€”3 sub-boards.
 * A player wins by getting five of their marbles in a vertical, horizontal or diagonal row.
 * @author Raha Ahmadi
 * @version 0.0
 */
public class Board {
    // array of blocks
    private Block[] blocks;
    // 6*6 board
    private char[][] table;

    /**
     * Create a new board
     */
    public Board() {
        blocks = new Block[4];
        for (int i = 0; i < 4; i++)
            blocks[i] = new Block();
        table = new char[6][6];
    }

    /**
     * Initial 6*6 board
     * @param blockNUm block number
     * @param block array of discs in each block
     */
    public void initTable(int blockNUm, char[][] block) {
        if (blockNUm == 1)
            for (int i = 0; i < 3; i++)
                System.arraycopy(block[i], 0, table[i], 0, 3);
        if (blockNUm == 2)
            for (int i = 0; i < 3; i++)
                System.arraycopy(block[i], 0, table[i], 3, 3);
        if (blockNUm == 3)
            for (int i = 3; i < 6; i++)
                System.arraycopy(block[i - 3], 0, table[i], 0, 3);
        if (blockNUm == 4)
            for (int i = 3; i < 6; i++)
                System.arraycopy(block[i - 3], 0, table[i], 3, 3);
    }

    /**
     * initial 6*6 board with 4 blocks
     */
    public void updateTable(){
        initTable(1,blocks[0].getDiscs());
        initTable(2,blocks[1].getDiscs());
        initTable(3,blocks[2].getDiscs());
        initTable(4,blocks[3].getDiscs());
    }

    /**
     * check if move is valid
     * @param coordinate move coordinate
     * @return true or false
     */
    public boolean validMove(Coordinate coordinate) {
        int n = coordinate.findBlock();
        return blocks[n-1].validMove(coordinate);
    }

    /**
     * make move on board
     * @param player game player
     * @param coordinate move coordinate
     */
    public void move (Player player, Coordinate coordinate) {
        int n = coordinate.findBlock();
        blocks[n-1].move(player, coordinate);
    }

    /**
     * rotate a block
     * @param blockNum block number to rotate
     * @param rotation clockwise or anticlockwise
     */
    public void rotate(int blockNum , int rotation) {
        if (rotation == 1)
            blocks[blockNum-1].rotateClockwise();
        if (rotation == 2)
            blocks[blockNum-1].rotateAnticlockwise();
    }

    /**
     * check if the player win the game
     * @param ch player color code
     * @return true or false
     */
    public boolean checkWin(char ch) {
        updateTable();
        boolean flag = false;
        outer:
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (table[i][j] == ch) {
                    //up right
                    if ((i == 5 && (j == 0 || j == 1)) || (i == 4 && (j == 0 || j == 1))) {
                        int count = 1;
                        int x = i-1, y = j+1;
                        while (table[x][y] == ch) {
                            x--;
                            y++;
                            count++;
                            if (count == 5) {
                                flag = true;
                                break outer;
                            }
                        }
                    }
                    // up left
                    if ((i == 5 && (j == 5 || j == 4)) || (i == 4 && (j == 4 || j == 5))) {
                        int count = 1, x = i-1, y = j-1;
                        while (table[x][y] == ch) {
                            x--;
                            y--;
                            count++;
                            if (count == 5) {
                                flag = true;
                                break outer;
                            }
                        }
                    }
                    // down right
                    if ((i == 0 && (j == 0 || j == 1)) || (i == 1 && (j == 0 || j == 1))) {
                        int count = 1, x = i+1, y = j+1;
                        while (table[x][y] == ch) {
                            count++;
                            x++;
                            y++;
                            if (count == 5) {
                                flag = true;
                                break outer;
                            }
                        }
                    }
                    // down left
                    if ((i == 0 && (j == 5 || j == 4)) || (i == 1 && (j == 4 || j == 5))) {
                        int count = 1, x = i+1, y = j-1;
                        while (table[x][y] == ch) {
                            x++;
                            y--;
                            count++;
                            if (count == 5) {
                                flag = true;
                                break outer;
                            }
                        }
                    }
                    //right
                    if (j == 0 || j == 1) {
                        int count = 1;
                        int y = j+1;
                        while (table[i][y] == ch) {
                            y++;
                            count++;
                            if (count == 5) {
                                flag = true;
                                break outer;
                            }
                        }
                    }
                    //left
                    if (j == 4 || j == 5) {
                        int count = 1;
                        int y = j-1;
                        while (table[i][y] == ch) {
                            y--;
                            count++;
                            if (count == 5) {
                                flag = true;
                                break outer;
                            }
                        }
                    }
                    //up
                    if (i == 4 || i == 5) {
                        int count = 1, x = i-1;
                        while (table[x][j] == ch) {
                            x--;
                            count++;
                            if (count == 5) {
                                flag = true;
                                break outer;
                            }
                        }
                    }
                    //down
                    if (i == 0 || i == 1) {
                        int count = 1, x = i+1;
                        while (table[x][j] == ch) {
                            x++;
                            count++;
                            if (count == 5) {
                                flag = true;
                                break outer;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * show the winner
     */
    public void winner() {
        if (checkWin('B') && !checkWin('R'))
            System.out.println("Black is winner");
        if (checkWin('R') && !checkWin('B'))
            System.out.println("Red is winner");
        if (checkWin('B') && checkWin('R'))
            System.out.println("No one win");
    }

    /**
     * print board
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < 3; i++)
            res.append(blocks[0].getLine(i)).append("| ").append(blocks[1].getLine(i)).append("\n");
        for(int i = 0; i  <14; i++) {
            if(i == 6)
                res.append("+");
            else
                res.append("-");
        }
        res.append("\n");
        for(int i = 0; i < 3; i++)
            res.append(blocks[2].getLine(i)).append("| ").append(blocks[3].getLine(i)).append("\n");
        return res.toString();
    }
}
