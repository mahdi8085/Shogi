import board.Board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // table
        char[][] table = {
                {'l', 'b', 's', 'g', 'k'},
                {'-', '-', '-', '-', 'p'},
                {'-', '-', '-', '-', '-'},
                {'P', '-', '-', '-', '-'},
                {'K', 'G', 'S', 'B', 'L'}
        };

        // print table and receive input in a loop
        Board board = new Board(table);
        Scanner scanner = new Scanner(System.in);
        String input = null;

        while (board.getPiece().gameOver()) {

            // input
            input = scanner.nextLine();

            // finish game
            if (input.charAt(0) == 48) {
                return;
            }

            // optimize inputs
            int x1 = (int) input.charAt(2) - 48;
            int y1 = (int) input.charAt(3) - 48;
            int temp = x1;
            x1 = y1;
            y1 = temp;
            x1 = 5 - x1;
            y1 = y1 - 1;
            int x2 = (int) input.charAt(5) - 48;
            int y2 = (int) input.charAt(6) - 48;
            temp = x2;
            x2 = y2;
            y2 = temp;
            x2 = 5 - x2;
            y2 = y2 - 1;

            // process game
            if (board.checkEverything(input.charAt(0), x1, y1, x2, y2)) {
                if ((int) input.charAt(2) - 48 == 0 && (int) input.charAt(3) - 48 == 0) {
                    board.setFromHand(input.charAt(0), x2, y2);
                }
                else {
                    board.setPieceTable(input.charAt(0), x2, y2, x1, y1);
                    board.removePieceTable(input.charAt(0), x1, y1);
                }
                board.getPiece().setCheckTurn(!board.getPiece().isCheckTurn());
                board.printTable();
                board.printList(board.getBlackInHandPieces());
                board.printList(board.getWhiteInHandPieces());
            }
            else {
                board.printTable();
                board.printList(board.getBlackInHandPieces());
                board.printList(board.getWhiteInHandPieces());
            }
        }
    }
}