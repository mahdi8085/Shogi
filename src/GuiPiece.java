import java.util.LinkedList;
import java.util.Objects;

public class GuiPiece {

    private int xp;
    private int yp;
    private int x;
    private int y;
    private String name;
    private boolean isWhite;
    private boolean isUpgraded;
    private boolean isInHand;
    private LinkedList<GuiPiece> pieces;

    // constructor
    public GuiPiece(int xp, int yp, String name, boolean isWhite, LinkedList<GuiPiece> pieces) {
        this.xp = xp;
        this.yp = yp;
        this.x = xp * 64;
        this.y = yp * 64;
        this.name = name;
        this.isWhite = isWhite;
        this.isUpgraded = false;
        this.isInHand = false;
        this.pieces = pieces;
        this.pieces.add(this);
    }

    // getters and setters

    public int getXp() {
        return xp;
    }

    public int getYp() {
        return yp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isUpgraded() {
        return isUpgraded;
    }

    public boolean isInHand() {
        return isInHand;
    }

    public LinkedList<GuiPiece> getPieces() {
        return pieces;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setYp(int yp) {
        this.yp = yp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public void setUpgraded(boolean upgraded) {
        isUpgraded = upgraded;
    }

    public void setInHand(boolean inHand) {
        isInHand = inHand;
    }

    public void setPieces(LinkedList<GuiPiece> pieces) {
        this.pieces = pieces;
    }

    public static int blackInHandPiecesNumber = 0;
    public static int whiteInHandPiecesNumber = 0;
    public static boolean isBlackTurn = true;

    public void move(int xp, int yp) {
        if (isBlackTurn) {
            if (this.isWhite) {
                this.x = this.xp * 64;
                this.y = this.yp * 64;
                return;
            }
        }
        else {
            if (!this.isWhite) {
                this.x = this.xp * 64;
                this.y = this.yp * 64;
                return;
            }
        }

        if (GuiMain.getPiece(xp * 64, yp * 64) != null) {
            if (Objects.requireNonNull(GuiMain.getPiece(xp * 64, yp * 64)).isWhite != this.isWhite
                    && xp < 5 && xp > -1 && yp < 5 && yp > -1) {
                if (this.xp == 6 || this.xp == 5) {
                    this.x = this.xp * 64;
                    this.y = this.yp * 64;
                    return;
                }
                if (checkValidMovement(this.name, this.xp, this.yp, xp, yp) && this.xp != 5 && this.xp != 6) {
                    Objects.requireNonNull(GuiMain.getPiece(xp * 64, yp * 64)).remove();
                }
            }
            else {
                this.x = this.xp * 64;
                this.y = this.yp * 64;
                return;
            }
        }

        if (xp < 5 && xp > -1 && yp < 5 && yp > -1) {
            if (this.xp == 6) {
                whiteInHandPiecesNumber--;
                this.isInHand = false;
                this.xp = xp;
                this.yp = yp;
                this.x = xp * 64;
                this.y = yp * 64;
                isBlackTurn =! isBlackTurn;
                return;
            }
            if (this.xp == 5) {
                blackInHandPiecesNumber--;
                this.isInHand = false;
                this.xp = xp;
                this.yp = yp;
                this.x = xp * 64;
                this.y = yp * 64;
                isBlackTurn =! isBlackTurn;
                return;
            }
            if (checkValidMovement(this.name, this.xp, this.yp, xp, yp)
                    && this.xp != 5 && this.xp != 6) {
                this.xp = xp;
                this.yp = yp;
                this.x = xp * 64;
                this.y = yp * 64;
                if (this.isWhite && yp < 2 && yp > -1) {
                    this.isUpgraded = true;
                }
                else if (!this.isWhite && yp > 2 && yp < 5) {
                    this.isUpgraded = true;
                }
                isBlackTurn =! isBlackTurn;
            }
            else {
                this.x = this.xp * 64;
                this.y = this.yp * 64;
            }
        }
        else {
            this.x = this.xp * 64;
            this.y = this.yp * 64;
        }
    }

    public void remove() {
        if (Objects.equals(this.name, "king")) {
            if (this.isWhite) {
                System.out.println("black wins!");
                System.exit(0);
            } else {
                System.out.println("white wins!");
                System.exit(0);
            }
        }
        this.isWhite = !this.isWhite;
        this.isUpgraded = false;
        this.isInHand = true;
        if (this.isWhite) {
            this.xp = 6;
            this.x = this.xp * 64;
            this.yp = whiteInHandPiecesNumber;
            this.y = this.yp * 64;
            whiteInHandPiecesNumber++;
        } else {
            this.xp = 5;
            this.x = this.xp * 64;
            this.yp = blackInHandPiecesNumber;
            this.y = this.yp * 64;
            blackInHandPiecesNumber++;
        }
    }

    public boolean checkValidMovement(String piece, int x1, int y1, int x2, int y2) {
        if (piece.equalsIgnoreCase("pawn")) {
            if (!this.isUpgraded) {
                if (!this.isWhite) {
                    return x2 == x1 && y2 == y1 + 1;
                }
                else {
                    return x2 == x1 && y2 == y1 - 1;
                }
            }
            else {
                if (!this.isWhite) {
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
        }
        else if (piece.equalsIgnoreCase("javelin")) {
            if (!this.isUpgraded) {
                if (!this.isWhite) {
                    return x2 == x1 && y2 > y1;
                }
                else {
                    return x2 == x1 && y2 < y1;
                }
            }
            else {
                return (x2 > x1 && y2 == y1)
                        || (x2 < x1 && y2 == y1)
                        || (x2 == x1 && y2 > y1)
                        || (x2 == x1 && y2 < y1);
            }
        }
        else if (piece.equalsIgnoreCase("bishop")) {
            boolean v = Math.abs(x2 - x1) == Math.abs(y2 - y1);
            if (!this.isUpgraded) {
                if (!this.isWhite) {
                    return (x2 > x1 && y2 > y1 && v) || (x2 < x1 && y2 > y1 && v);
                }
                else {
                    return (x2 < x1 && y2 < y1 && v) || (x2 > x1 && y2 < y1 && v);
                }
            }
            else {
                return (x2 > x1 && y2 > y1)
                        || (x2 < x1 && y2 > y1)
                        || (x2 < x1 && y2 < y1)
                        || (x2 > x1 && y2 < y1)
                        || (x2 == x1 + 1 && y2 == y1)
                        || (x2 == x1 - 1 && y2 == y1)
                        || (x2 == x1 && y2 == y1 + 1)
                        || (x2 == x1 && y2 == y1 - 1);
            }
        }
        else if (piece.equalsIgnoreCase("silverGeneral")) {
            if (!this.isUpgraded) {
                if (!this.isWhite) {
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
            else {
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
        }
        else if (piece.equalsIgnoreCase("goldenGeneral")) {
            if (!this.isWhite) {
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
        else if (piece.equalsIgnoreCase("king")) {
            if (!this.isWhite) {
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
}