package board;

import pieces.Piece;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Board {

    private Piece piece;

    // white player
    private Dictionary<Integer, Character> whitePieces;
    private ArrayList<Integer> whitePiecesNumbers;
    private Dictionary<Integer, Character> whiteUpgradedPieces;
    private ArrayList<Integer> whiteUpgradedPiecesNumbers;
    private ArrayList<Integer> whiteInHandPieces;

    // black player
    private Dictionary<Integer, Character> blackPieces;
    private ArrayList<Integer> blackPiecesNumbers;
    private Dictionary<Integer, Character> blackUpgradedPieces;
    private ArrayList<Integer> blackUpgradedPiecesNumbers;
    private ArrayList<Integer> blackInHandPieces;

    // board of the game
    private char[][] table;

    // constructor
    public Board(char[][] table) {
        this.piece = new Piece(this);
        whitePieces = new Hashtable<>();
        whitePiecesNumbers = new ArrayList<>();
        whitePieces.put(40, 'K'); whitePiecesNumbers.add(40);
        whitePieces.put(41, 'G'); whitePiecesNumbers.add(41);
        whitePieces.put(42, 'S'); whitePiecesNumbers.add(42);
        whitePieces.put(43, 'B'); whitePiecesNumbers.add(43);
        whitePieces.put(44, 'L'); whitePiecesNumbers.add(44);
        whitePieces.put(30, 'P'); whitePiecesNumbers.add(30);
        whiteUpgradedPieces = new Hashtable<>();
        whiteUpgradedPiecesNumbers = new ArrayList<>();
        whiteInHandPieces = new ArrayList<>();
        blackPieces = new Hashtable<>();
        blackPiecesNumbers = new ArrayList<>();
        blackPieces.put(4, 'k'); blackPiecesNumbers.add(4);
        blackPieces.put(3, 'g'); blackPiecesNumbers.add(3);
        blackPieces.put(2, 's'); blackPiecesNumbers.add(2);
        blackPieces.put(1, 'b'); blackPiecesNumbers.add(1);
        blackPieces.put(0, 'l'); blackPiecesNumbers.add(0);
        blackPieces.put(14, 'p'); blackPiecesNumbers.add(14);
        blackUpgradedPieces = new Hashtable<>();
        blackUpgradedPiecesNumbers = new ArrayList<>();
        blackInHandPieces = new ArrayList<>();
        this.table = table;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Dictionary<Integer, Character> getWhitePieces() {
        return whitePieces;
    }

    public void setWhitePieces(Dictionary<Integer, Character> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public Dictionary<Integer, Character> getWhiteUpgradedPieces() {
        return whiteUpgradedPieces;
    }

    public void setWhiteUpgradedPieces(Dictionary<Integer, Character> whiteUpgradedPieces) {
        this.whiteUpgradedPieces = whiteUpgradedPieces;
    }

    public Dictionary<Integer, Character> getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(Dictionary<Integer, Character> blackPieces) {
        this.blackPieces = blackPieces;
    }

    public Dictionary<Integer, Character> getBlackUpgradedPieces() {
        return blackUpgradedPieces;
    }

    public void setBlackUpgradedPieces(Dictionary<Integer, Character> blackUpgradedPieces) {
        this.blackUpgradedPieces = blackUpgradedPieces;
    }

    public ArrayList<Integer> getWhitePiecesNumbers() {
        return whitePiecesNumbers;
    }

    public void setWhitePiecesNumbers(ArrayList<Integer> whitePiecesNumbers) {
        this.whitePiecesNumbers = whitePiecesNumbers;
    }

    public ArrayList<Integer> getWhiteUpgradedPiecesNumbers() {
        return whiteUpgradedPiecesNumbers;
    }

    public void setWhiteUpgradedPiecesNumbers(ArrayList<Integer> whiteUpgradedPiecesNumbers) {
        this.whiteUpgradedPiecesNumbers = whiteUpgradedPiecesNumbers;
    }

    public ArrayList<Integer> getBlackPiecesNumbers() {
        return blackPiecesNumbers;
    }

    public void setBlackPiecesNumbers(ArrayList<Integer> blackPiecesNumbers) {
        this.blackPiecesNumbers = blackPiecesNumbers;
    }

    public ArrayList<Integer> getBlackUpgradedPiecesNumbers() {
        return blackUpgradedPiecesNumbers;
    }

    public void setBlackUpgradedPiecesNumbers(ArrayList<Integer> blackUpgradedPiecesNumbers) {
        this.blackUpgradedPiecesNumbers = blackUpgradedPiecesNumbers;
    }

    public ArrayList<Integer> getWhiteInHandPieces() {
        return whiteInHandPieces;
    }

    public void setWhiteInHandPieces(ArrayList<Integer> whiteInHandPieces) { this.whiteInHandPieces = whiteInHandPieces; }

    public ArrayList<Integer> getBlackInHandPieces() {
        return blackInHandPieces;
    }

    public void setBlackInHandPieces(ArrayList<Integer> blackInHandPieces) { this.blackInHandPieces = blackInHandPieces; }

    public char[][] getTable() {
        return this.table;
    }

    public void setPieceTable(char piece, int i, int j, int I, int J) {
        if (this.whiteUpgradedPiecesNumbers.contains(I * 10 + J) && this.whiteUpgradedPieces.get(I * 10 + J) == piece) {
            this.setUpgradedPieceTable(piece, i , j);
        }
        else if (this.blackUpgradedPiecesNumbers.contains(I * 10 + J) && this.blackUpgradedPieces.get(I * 10 + J) == piece) {
            this.setUpgradedPieceTable(piece, i, j);
        }
        else {
            if (this.table[i][j] != '-') {
                if (this.table[i][j] > 64 && this.table[i][j] < 91) {
                    this.blackInHandPieces.add(this.table[i][j] + 32);
                    if (this.whiteUpgradedPiecesNumbers.contains(i * 10 + j) && this.whiteUpgradedPieces.get(i * 10 + j) == this.table[i][j]) {
                        this.removeUpgradedPieceTable(this.table[i][j], i, j);
                    }
                    else {
                        this.removePieceTable(this.table[i][j], i, j);
                    }
                }
                else {
                    this.whiteInHandPieces.add(this.table[i][j] - 32);
                    if (this.blackUpgradedPiecesNumbers.contains(i * 10 + j) && this.blackUpgradedPieces.get(i * 10 + j) == this.table[i][j]) {
                        this.removeUpgradedPieceTable(this.table[i][j], i, j);
                    }
                    else {
                        this.removePieceTable(this.table[i][j], i, j);
                    }
                }
            }
            if (piece > 64 && piece < 91) {
                if (i < 2) {
                    this.whiteUpgradedPiecesNumbers.add(i * 10 + j);
                    this.whiteUpgradedPieces.put(i * 10 + j, piece);
                }
                else {
                    this.whitePiecesNumbers.add(i * 10 + j);
                    this.whitePieces.put(i * 10 + j, piece);
                }
            } else {
                if (i > 2) {
                    this.blackUpgradedPiecesNumbers.add(i * 10 + j);
                    this.blackUpgradedPieces.put(i * 10 + j, piece);
                }
                else {
                    this.blackPiecesNumbers.add(i * 10 + j);
                    this.blackPieces.put(i * 10 + j, piece);
                }
            }
            this.table[i][j] = piece;
        }
    }

    public void setUpgradedPieceTable(char piece, int i, int j) {
        if (this.table[i][j] != '-') {
            if (this.table[i][j] > 64 && this.table[i][j] < 91) {
                this.blackInHandPieces.add(this.table[i][j] + 32);
                this.removeUpgradedPieceTable(this.table[i][j], i, j);
            }
            else {
                this.whiteInHandPieces.add(this.table[i][j] - 32);
                this.removeUpgradedPieceTable(this.table[i][j], i, j);
            }
        }
        if (piece > 64 && piece < 91) {
            this.whiteUpgradedPiecesNumbers.add(i * 10 + j);
            this.whiteUpgradedPieces.put(i * 10 + j, piece);
        }
        else {
            this.blackUpgradedPiecesNumbers.add(i * 10 + j);
            this.blackUpgradedPieces.put(i * 10 + j, piece);
        }
        this.table[i][j] = piece;
    }

    public void setFromHand(char piece, int i, int j) {
        if (piece > 64 && piece < 91) {
            int n = piece;
            this.whiteInHandPieces.remove((Integer) n);
            this.whitePiecesNumbers.add(i * 10 + j);
            this.whitePieces.put(i * 10 + j, piece);
        }
        else {
            int n = piece;
            this.blackInHandPieces.remove((Integer) n);
            this.blackPiecesNumbers.add(i * 10 + j);
            this.blackPieces.put(i * 10 + j, piece);
        }
        this.table[i][j] = piece;
    }

    public void removePieceTable(char piece, int i, int j) {
        if (piece > 64 && piece < 91) {
            int n = i * 10 + j;
            this.whitePiecesNumbers.remove((Integer) n);
            this.whitePieces.remove(i * 10 + j);
        }
        else {
            int n = i * 10 + j;
            this.blackPiecesNumbers.remove((Integer) n);
            this.blackPieces.remove(i * 10 + j);
        }
        this.table[i][j] = '-';
    }

    public void removeUpgradedPieceTable(char piece, int i, int j) {
        if (piece > 64 && piece < 91) {
            int n = i * 10 + j;
            this.whiteUpgradedPiecesNumbers.remove((Integer) n);
            this.whiteUpgradedPieces.remove(i * 10 + j);
        }
        else {
            int n = i * 10 + j;
            this.blackUpgradedPiecesNumbers.remove((Integer) n);
            this.blackUpgradedPieces.remove(i * 10 + j);
        }
        this.table[i][j] = '-';
    }

    public void printTable() {
        for (int i = 4; i > -1; i--) {
            for (int j = 0; j < 5; j++) {
                System.out.print(this.table[i][j]);
            }
        }
        System.out.println();
    }

    public void printList(List list) {
        for (Object i : list) {
            StringBuilder stringBuilder = new StringBuilder();
            int x = (int) i;
            stringBuilder.append((char) x);
            System.out.print(stringBuilder.toString());
        }
        System.out.println();
    }

    public boolean checkRange(int i, int j) {
        return i >= 0 && i <= 4 && j >= 0 && j <= 4;
    }

    public boolean checkDestination(char piece, int i, int j) {
        if (piece > 64 && piece < 91) {
            return this.table[i][j] <= 64 || this.table[i][j] >= 91;
        }
        else if (piece > 96 && piece < 123) {
            return this.table[i][j] <= 96 || this.table[i][j] >= 123;
        }
        else {
            return true;
        }
    }

    public boolean checkJumpPiece(int x1, int y1, int x2, int y2) {
        if (x1 == 5 && y1 == -1) {
            return this.table[x2][y2] == '-';
        }
        if (x1 == x2) {
            if (y2 > y1) {
                for (int i = 1; i < Math.abs(y2 - y1); i++) {
                    if (this.table[x1][y1 + i] != '-') {
                        return false;
                    }
                }
            }
            else {
                for (int i = 1; i < Math.abs(y2 - y1); i++) {
                    if (this.table[x1][y1 - i] != '-') {
                        return false;
                    }
                }
            }
            return true;
        }
        else if (y2 == y1) {
            if (x2 > x1) {
                for (int i = 1; i < Math.abs(x2 - x1); i++) {
                    if (this.table[x1 + i][y1] != '-') {
                        return false;
                    }
                }
            }
            else {
                for (int i = 1; i < Math.abs(x2 - x1); i++) {
                    if (this.table[x1 - i][y1] != '-') {
                        return false;
                    }
                }
            }
            return true;
        }
        else {
            if (Math.abs(x2 - x1) != Math.abs(y2 - y1)) {
                return false;
            }
            if (x2 > x1 && y2 > y1) {
                for (int i = 1; i < Math.abs(x2 - x1); i++) {
                    if (this.table[x1 + i][y1 + i] != '-') {
                        return false;
                    }
                }
            }
            else if (x2 > x1) {
                for (int i = 1; i < Math.abs(x2 - x1); i++) {
                    if (this.table[x1 + i][y1 - i] != '-') {
                        return false;
                    }
                }
            }
            else if (y2 > y1) {
                for (int i = 1; i < Math.abs(x2 - x1); i++) {
                    if (this.table[x1 - i][y1 + i] != '-') {
                        return false;
                    }
                }
            }
            else {
                for (int i = 1; i < Math.abs(x2 - x1); i++) {
                    if (this.table[x1 - i][y1 - i] != '-') {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public boolean checkEverything(char piece, int x1, int y1, int x2, int y2) {
//        System.out.println("CR1: " + checkRange(x1, y1) + " X1: " + x1 + " Y1: " + y1);
//        System.out.println("CR2: " + checkRange(x2, y2) + " X2: " + x2 + " Y2: " + y2);
        //System.out.println("PC: " + this.piece.checkMovement(piece, 5 - x1, y1 + 1, 5 - x2, y2 + 1));

        // set from hand
        if (this.piece.checkTurns(piece) && 5 - x1 == 0 && y1 + 1 == 0 && checkRange(x2, y2)) {
            if (this.whiteInHandPieces.contains((int) piece) && this.table[x2][y2] == '-') {
                return true;
            }
            return this.blackInHandPieces.contains((int) piece) && this.table[x2][y2] == '-';
        }

        // set from table
        if (this.piece.checkTurns(piece) && checkRange(x1, y1) && checkRange(x2, y2)) {
            if (checkJumpPiece(x1, y1, x2, y2) && checkDestination(piece, x2, y2)) {
                if (this.whitePiecesNumbers.contains(x1 * 10 + y1)) {
                    if (this.whitePieces.get(x1 * 10 + y1) == piece) {
                        return this.piece.checkMovement(piece, y1 + 1, 5 - x1, y2 + 1, 5 - x2);
                    }
                }
                if (this.whiteUpgradedPiecesNumbers.contains(x1 * 10 + y1)) {
                    if (this.whiteUpgradedPieces.get(x1 * 10 + y1) == piece) {
                        return this.piece.checkUpgradedMovement(piece, y1 + 1, 5 - x1, y2 + 1, 5 - x2);
                    }
                }
                if (this.blackPiecesNumbers.contains(x1 * 10 + y1)) {
                    if (this.blackPieces.get(x1 * 10 + y1) == piece) {
                        return this.piece.checkMovement(piece, y1 + 1, 5 - x1, y2 + 1, 5 - x2);
                    }
                }
                if (this.blackUpgradedPiecesNumbers.contains(x1 * 10 + y1)) {
                    if (this.blackUpgradedPieces.get(x1 * 10 + y1) == piece) {
                        return this.piece.checkUpgradedMovement(piece, y1 + 1, 5 - x1, y2 + 1, 5 - x2);
                    }
                }
            }
        }
        return false;
    }
}
