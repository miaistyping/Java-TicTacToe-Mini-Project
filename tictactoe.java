import java.util.*;
public class tictactoe {

    static String[][] board;

    /* printBoard() prints a graphic representation of the board to the terminal. */
    static void printBoard() {
        System.out.println("    1   2   3  ");
        System.out.println("  |---|---|---|");
        System.out.println("1 | " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("  |-----------|");
        System.out.println("2 | " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("  |-----------|");
        System.out.println("3 | " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("  |---|---|---|");
    }

    /* placeMove() places the player's respective symbol onto the board at the row and column they specified.
    *  Because the printed board is ordered from 1 to 3, we have to decrement the passed in row and column numbers
    *  by 1. */
    static void placeMove(int row, int col, String symbol) {
        board[row - 1][col - 1] = symbol;
    }

    /* checkWinner() checks for a win condition on the board by finding all possible lines
    *  and checking if any of those lines equate to XXX or OOO to determine who wins.
    *  If no one is yet found to win and the board still contains whitespace, the game continues.
    *  Otherwise, if there is no whitespace, the game is a stalemate. */
    static int checkWinner() {
        int i = 0;

        // 8 cases for 8 win conditions
        while (i < 8) {
            String line = null;
            switch (i) {
                case 0:
                    line = board[0][0] + board[0][1] + board[0][2];
                    break;
                case 1:
                    line = board[1][0] + board[1][1] + board[1][2];
                    break;
                case 2:
                    line = board[2][0] + board[2][1] + board[2][2];
                    break;
                case 3:
                    line = board[0][0] + board[1][0] + board[2][0];
                    break;
                case 4:
                    line = board[0][1] + board[1][1] + board[2][1];
                    break;
                case 5:
                    line = board[0][2] + board[1][2] + board[2][2];
                    break;
                case 6:
                    line = board[0][0] + board[1][1] + board[2][2];
                    break;
                case 7:
                    line = board[0][2] + board[1][1] + board[2][0];
                    break;
            }

            if (line.equals("XXX")) {
                // Player 1 wins
                return 1;
            }

            if (line.equals("OOO")) {
                // Player 2 wins
                return 2;
            }

            i++;
        }

        // Check board for whitespace
        for(int r = 0 ; r < board.length; r++) {
            for(int c = 0 ; c < board.length; c++) {
                if(board[r][c].equals(" ")) {
                    // Game is not yet complete
                    return 0;
                }
            }
        }
        // Stalemate, no one wins
        return 3;
    }

    /* announceWinner() prints who wins the game, or if it's a stalemate. */
    static void announceWinner(int winner) {
        if (winner != 3) {
            System.out.println("Player " + winner + " wins!");
        } else {
            System.out.println("Stalemate.");
        }
    }

    /* main() sets up the board, contains base dialogue for the game, and prompts the players to make their moves. */
    public static void main(String[] args) {
        board = new String[3][3];
        Scanner scan = new Scanner(System.in);
        boolean playing = true;
        int row = 0, col = 0;

        // At beginning of game, board is filled with whitespace
        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Try to get 3 Xs or 3 Os in a row to win.");
        System.out.println("Player 1 will be X and Player 2 will be O.");
        System.out.println("Lets start!");

        int playerNum = 1;
        int winner = 0;
        while (playing) {
            printBoard();
            System.out.println("Player " + playerNum + ", make your move.");
            System.out.println("Specify the row and column you would like to make your move.");

            System.out.print("Row: ");
            try {
                row = scan.nextInt();
                if (!(row > 0 && row <= 3)) {
                    System.out.println("Invalid input; re-enter row number(1, 2, or 3): ");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter row number(1, 2, or 3): ");
                continue;
            }

            System.out.print("Column: ");
            try {
                col = scan.nextInt();
                if (!(col > 0 && col <= 3)) {
                    System.out.println("Invalid input; re-enter column number(1, 2, or 3): ");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter column number(1, 2, or 3): ");
                continue;
            }

            if (playerNum == 1) {
                placeMove(row, col, "X");
                playerNum++;
            } else {
                placeMove(row, col, "O");
                playerNum--;
            }

            winner = checkWinner();

            if (winner != 0) {
                playing = false;
            }
        }
        printBoard();

        announceWinner(winner);
    }
}