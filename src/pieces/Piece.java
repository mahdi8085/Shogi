package pieces;

import board.Board;

public class Piece {

    private Board board;
    private boolean checkTurn;

    // constructor
    public Piece(Board board) {
        this.board = board;
        this.checkTurn = false;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isCheckTurn() {
        return checkTurn;
    }

    public void setCheckTurn(boolean checkTurn) {
        this.checkTurn = checkTurn;
    }

    public boolean checkMovement(char piece, int x1, int y1, int x2, int y2) {
        if (piece == 'P' || piece == 'p') {
            if (piece == 'P') {
                return x2 == x1 && y2 == y1 + 1;
            }
            else {
                return x2 == x1 && y2 == y1 - 1;
            }
        }
        else if (piece == 'L' || piece == 'l') {
            if (piece == 'L') {
                return x2 == x1 && y2 > y1;
            }
            else {
                return x2 == x1 && y2 < y1;
            }
        }
        else if (piece == 'B' || piece == 'b') {
            boolean v = Math.abs(x2 - x1) == Math.abs(y2 - y1);
            if (piece == 'B') {
                return (x2 > x1 && y2 > y1 && v) || (x2 < x1 && y2 > y1 && v);
            }
            else {
                return (x2 < x1 && y2 < y1 && v) || (x2 > x1 && y2 < y1 && v);
            }
        }
        else if (piece == 'S' || piece == 's') {
            if (piece == 'S') {
                return (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1 + 1)
                        || (x2 == x1 - 1 && y2 == y1 - 1);
            }
            else {
                return (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1 - 1)
                        || (x2 == x1 + 1 && y2 == y1 + 1);
            }
        }
        else if (piece == 'G' || piece == 'g') {
            if (piece == 'G') {
                return (x2 == x1 + 1 && y2 == y1)
                        || (x2 == x1 + 1 && y2 == y1 + 1)
                        || (x2 == x1 - 1 && y2 == y1 + 1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1);
            }
            else {
                return (x2 == x1 - 1 && y2 == y1)
                        || (x2 == x1 - 1 && y2 == y1 - 1)
                        || (x2 == x1 + 1 && y2 == y1 - 1)
                        || (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1);
            }
        }
        else if (piece == 'K' || piece == 'k') {
            if (piece == 'K') {
                return (x2 == x1 + 1 && y2 == y1)
                        || (x2 == x1 - 1 && y2 == y1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 + 1 && y2 == y1 + 1)
                        || (x2 == x1 - 1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1 - 1);
            }
            else {
                return (x2 == x1 - 1 && y2 == y1)
                        || (x2 == x1 + 1 && y2 == y1)
                        || (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 - 1 && y2 == y1 - 1)
                        || (x2 == x1 + 1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1 + 1);
            }
        }
        else {
            return false;
        }
    }

    public boolean checkUpgradedMovement(char piece, int x1, int y1, int x2, int y2) {
        if (piece == 'P' || piece == 'p' || piece == 'G' || piece == 'g') {
            if (piece == 'P' || piece == 'G') {
                return (x2 == x1 + 1 && y2 == y1)
                        || (x2 == x1 + 1 && y2 == y1 + 1)
                        || (x2 == x1 - 1 && y2 == y1 + 1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1);
            }
            else {
                return (x2 == x1 - 1 && y2 == y1)
                        || (x2 == x1 - 1 && y2 == y1 - 1)
                        || (x2 == x1 + 1 && y2 == y1 - 1)
                        || (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1);
            }
        }
        else if (piece == 'L' || piece == 'l') {
            return (x2 > x1 && y2 == y1)
                    || (x2 < x1 && y2 == y1)
                    || (x2 == x1 && y2 > y1)
                    || (x2 == x1 && y2 < y1);
        }
        else if (piece == 'B' || piece == 'b') {
            return (x2 > x1 && y2 > y1)
                    || (x2 < x1 && y2 > y1)
                    || (x2 < x1 && y2 < y1)
                    || (x2 > x1 && y2 < y1)
                    || (x2 == x1 + 1 && y2 == y1)
                    || (x2 == x1 - 1 && y2 == y1)
                    || (x2 == x1 && y2 == y1 + 1)
                    || (x2 == x1 && y2 == y1 - 1);
        }
        else if (piece == 'S' || piece == 's') {
            return (x2 == x1 + 2 && y2 == y1)
                    || (x2 == x1 + 1 && y2 == y1)
                    || (x2 == x1 - 2 && y2 == y1)
                    || (x2 == x1 - 1 && y2 == y1)
                    || (x2 == x1 && y2 == y1 + 2)
                    || (x2 == x1 && y2 == y1 + 1)
                    || (x2 == x1 && y2 == y1 - 2)
                    || (x2 == x1 && y2 == y1 - 1)
                    || (x2 == x1 + 2 && y2 == y1 + 2)
                    || (x2 == x1 + 1 && y2 == y1 + 1)
                    || (x2 == x1 - 2 && y2 == y1 + 2)
                    || (x2 == x1 - 1 && y2 == y1 + 1)
                    || (x2 == x1 + 2 && y2 == y1 - 2)
                    || (x2 == x1 + 1 && y2 == y1 - 1)
                    || (x2 == x1 - 2 && y2 == y1 - 2)
                    || (x2 == x1 - 1 && y2 == y1 - 1);
        }
        else if (piece == 'K' || piece == 'k') {
            if (piece == 'K') {
                return (x2 == x1 + 1 && y2 == y1)
                        || (x2 == x1 - 1 && y2 == y1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 + 1 && y2 == y1 + 1)
                        || (x2 == x1 - 1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1 - 1);
            }
            else {
                return (x2 == x1 - 1 && y2 == y1)
                        || (x2 == x1 + 1 && y2 == y1)
                        || (x2 == x1 && y2 == y1 - 1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 - 1 && y2 == y1 - 1)
                        || (x2 == x1 + 1 && y2 == y1 - 1)
                        || (x2 == x1 - 1 && y2 == y1 + 1)
                        || (x2 == x1 + 1 && y2 == y1 + 1);
            }
        }
        else {
            return false;
        }
    }

    public boolean gameOver() {
        if (this.board.getWhiteInHandPieces().contains((int) 'K')) {
            System.out.println("white wins!");
            return false;
        }
        else if (this.board.getBlackInHandPieces().contains((int) 'k')) {
            System.out.println("black wins!");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkTurns(char piece) {
        if (piece > 64 && piece < 91) {
            return checkTurn;
        }
        else if (piece > 96 && piece < 123) {
            return !checkTurn;
        }
        else {
            return false;
        }
    }
}
