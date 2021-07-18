import java.util.Random;
import java.util.Scanner;

public class GameTest {
    /**
     * start the game
     * @param board game board
     * @param player1 first player
     * @param player2 second player
     */
    private static void startGame(Board board, Player player1, Player player2) {
        Scanner input = new Scanner(System.in);
        System.out.println(board);
        System.out.println(player1.getColor() + " starts the game!");
        System.out.println("The movement format should be like this: x y (1 1)");
        while (true) {
            System.out.println(player1.getColor() + "'s turn");
            Coordinate coordinate;
            while (true) {
                int x1 = input.nextInt();
                int y1 = input.nextInt();
                coordinate = new Coordinate(x1, y1);
                if (coordinate.validCoordinate() && board.validMove(coordinate))
                    break;
                else
                    System.out.println("Invalid coordinate\ntry again");
            }
            board.move(player1, coordinate);
            System.out.println(board);
            if (board.checkWin(player1.getColorCode()))
                break;
            int rotate, blockNum;
            do {
                System.out.println("which block do you want to rotate?");
                blockNum = input.nextInt();
            } while (blockNum != 1 && blockNum != 2 && blockNum != 3 && blockNum != 4);
            do {
                System.out.println("enter 1 to rotate clockwise , enter 2 to rotate anticlockwise");
                rotate = input.nextInt();
            } while (rotate != 1 && rotate != 2);
            board.rotate(blockNum, rotate);
            System.out.println(board);
            if (board.checkWin(player1.getColorCode()))
                break;
            System.out.println(player2.getColor() + "'s turn");
            Coordinate coordinate2;
            while (true) {
                int x2 = input.nextInt();
                int y2 = input.nextInt();
                coordinate2 = new Coordinate(x2, y2);
                if (coordinate2.validCoordinate() && board.validMove(coordinate2))
                    break;
                else
                    System.out.println("Invalid coordinate\ntry again");
            }
            board.move(player2, coordinate2);
            System.out.println(board);
            if (board.checkWin(player2.getColorCode()))
                break;
            int rotate2, blockNum2;
            do {
                System.out.println("which block do you want to rotate?");
                blockNum2 = input.nextInt();
            } while (blockNum2 != 1 && blockNum2 != 2 && blockNum2 != 3 && blockNum2 != 4);
            do {
                System.out.println("enter 1 to rotate clockwise , enter 2 to rotate anticlockwise");
                rotate2 = input.nextInt();
            } while (rotate2 != 1 && rotate2 != 2);
            board.rotate(blockNum2, rotate2);
            System.out.println(board);
            if (board.checkWin(player2.getColorCode()))
                break;
        }
        board.winner();
    }
    public static void main (String[] args) {
        Board board = new Board();
        Player player1 = new Player("black");
        Player player2 = new Player("red");
        // select a player randomly to start the game.
        int item = new Random().nextInt(2);
        if (item == 0)
            startGame(board,player1,player2);
        if (item == 1)
            startGame(board,player2,player1);
    }
}
