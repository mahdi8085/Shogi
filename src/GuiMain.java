import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GuiMain {

    public static LinkedList<GuiPiece> pieces = new LinkedList<>();
    public static GuiPiece selectedPiece = null;

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame();
        frame.setBounds(480, 20, 480, 700);
        frame.setResizable(false);

        BufferedImage bufferedImage = ImageIO.read(new File
                ("C:\\Users\\user\\Desktop\\Documents\\programming\\Java\\assignments\\3\\src\\pieces.png"));
        Image[] image = new Image[12];
        int counter = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                image[counter] = bufferedImage.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                counter++;
            }
        }

        // black pieces
        GuiPiece bJavelin = new GuiPiece(0, 0, "javelin", false, pieces);
        GuiPiece bBishop = new GuiPiece(1, 0, "bishop", false, pieces);
        GuiPiece bSilverGeneral = new GuiPiece(2, 0, "silverGeneral", false, pieces);
        GuiPiece bGoldenGeneral = new GuiPiece(3, 0, "goldenGeneral", false, pieces);
        GuiPiece bKing = new GuiPiece(4, 0, "king", false, pieces);
        GuiPiece bPawn = new GuiPiece(4, 1, "pawn", false, pieces);

        // white pieces
        GuiPiece wJavelin = new GuiPiece(4, 4, "javelin", true, pieces);
        GuiPiece wBishop = new GuiPiece(3, 4, "bishop", true, pieces);
        GuiPiece wSilverGeneral = new GuiPiece(2, 4, "silverGeneral", true, pieces);
        GuiPiece wGoldenGeneral = new GuiPiece(1, 4, "goldenGeneral", true, pieces);
        GuiPiece wKing = new GuiPiece(0, 4, "king", true, pieces);
        GuiPiece wPawn = new GuiPiece(0, 3, "pawn", true, pieces);

        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics graphics) {
                boolean white = true;
                for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 5; x++) {
                        if (white) {
                            graphics.setColor(Color.green.darker());
                        }
                        else {
                            graphics.setColor(Color.yellow.darker());
                        }
                        graphics.fillRect(x * 64, y * 64, 64, 64);
                        white =! white;
                    }
                }
                for (GuiPiece p : pieces) {
                    int ind = 0;
                    if (p.getName().equalsIgnoreCase("king")) {
                        ind = 0;
                    }
                    if (p.getName().equalsIgnoreCase("goldenGeneral")) {
                        ind = 1;
                    }
                    if (p.getName().equalsIgnoreCase("bishop")) {
                        ind = 2;
                    }
                    if (p.getName().equalsIgnoreCase("silverGeneral")) {
                        ind = 3;
                    }
                    if (p.getName().equalsIgnoreCase("javelin")) {
                        ind = 4;
                    }
                    if (p.getName().equalsIgnoreCase("pawn")) {
                        ind = 5;
                    }
                    if (!p.isWhite()) {
                        ind += 6;
                    }
                    graphics.drawImage(image[ind], p.getX(), p.getY(), this);
                }
            }
        };

        frame.add(panel);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null) {
                    selectedPiece.setX(e.getX() - 32);
                    selectedPiece.setY(e.getY() - 32);
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println((getPiece(e.getX(), e.getY()).isWhite() ? "white " : "black ") + getPiece(e.getX(), e.getY()).getName());
                selectedPiece = getPiece(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX() / 64, e.getY() / 64);
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static GuiPiece getPiece(int x, int y) {
        for (GuiPiece p : pieces) {
            if (p.getXp() == x / 64 && p.getYp() == y / 64) {
                return p;
            }
        }
        return null;
    }
}