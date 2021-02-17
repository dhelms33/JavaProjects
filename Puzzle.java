/*
 * CS1050 - Computer Science I - Fall 2020
 * Instructor: Thyago Mota
 * Description: Prg03 - Puzzle
 * Student(s) Name(s): Dereck Helms
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Puzzle extends JPanel implements MouseListener {

    // class variables (given)
    static final String RES_FOLDER = "res";
    /* Since no longer a constant, has been camel cased */
    static String imageFilePrefix = "monet";
    static String imageFileSuffix = ".png";
    static final int TILE_SIZE = 150;
    static final int GRID_SIZE = 4;
    static final int INVALID_MOVE = -1;
    static final int MOVE_TILE_UP = 0;
    static final int MOVE_TILE_RIGHT = 1;
    static final int MOVE_TILE_DOWN = 2;
    static final int MOVE_TILE_LEFT = 3;

    // instance variables (given)
    private BufferedImage[][] original, current;

    // constructor (given)
    Puzzle() throws IOException {

    }
    Puzzle(String prefix, String suffix) throws IOException {
        imageFilePrefix = prefix;
        imageFileSuffix = suffix;
        loadImage();
        do {
            shuffle();
        } while (isSolved());
        addMouseListener(this);
    }

    // nothing to do
    private void loadImage() throws IOException {
        original = new BufferedImage[GRID_SIZE][GRID_SIZE];
        current = new BufferedImage[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++)
            for (int col = 0; col < GRID_SIZE; col++) {
                String imageFileName = imageFilePrefix + "-row-" + row + "-col-" + col + imageFileSuffix;
                original[row][col] = ImageIO.read(new File(RES_FOLDER + "/" + imageFileName));
                current[row][col] = original[row][col];
            }
        original[GRID_SIZE - 1][GRID_SIZE - 1] = current[GRID_SIZE - 1][GRID_SIZE - 1] = null;
    }

    // TODO: perform 1000 calls to moveTile passing random (but valid) rows and
    // columns at each time
    private void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            int row1 = rand.nextInt(GRID_SIZE);
            int row2 = rand.nextInt(GRID_SIZE);

            int col1 = rand.nextInt(GRID_SIZE);
            int col2 = rand.nextInt(GRID_SIZE);

            BufferedImage tmp = current[row1][col1];
            current[row1][col1] = current[row2][col2];
            current[row2][col2] = tmp;
        }

    }

    // TODO: return true/false depending whether the correspondent tile is empty or
    // not
    boolean isTileEmpty(int row, int col) {
        return current[row][col] == null;
    }

    // TODO: return the tile move (INVALID_MOVE, MOVE_TILE_UP, MOVE_TILE_RIGHT,
    // MOVE_TILE_DOWN, or MOVE_TILE_LEFT) based on the given row and col values
    int getTileMove(int row, int col) {
        if (isTileUpEmpty(row, col)) {
            return MOVE_TILE_UP;
        }
        if (isTileDownEmpty(row, col)) {
            return MOVE_TILE_DOWN;
        }

        if (isTileLeftEmpty(row, col)) {
            return MOVE_TILE_LEFT;
        }
        if (isTileRightEmpty(row, col)) {
            return MOVE_TILE_RIGHT;
        }
        return INVALID_MOVE;
    }

    // TODO: return true/false depending whether the tile right above the given row
    // and col values is empty
    boolean isTileUpEmpty(int row, int col) {
        return row > 0 && isTileEmpty(row - 1, col);
    }

    // TODO: return true/false depending whether the tile on the right side of the
    // given row and col values is empty
    boolean isTileRightEmpty(int row, int col) {
        return col < GRID_SIZE - 1 && isTileEmpty(row, col + 1);
    }

    // TODO: return true/false depending whether the tile right below the given row
    // and col values is empty
    boolean isTileDownEmpty(int row, int col) {
        return row < GRID_SIZE - 1 && isTileEmpty(row + 1, col);
    }

    // TODO: return true/false depending whether the tile on the left side of the
    // given row and col values is empty
    boolean isTileLeftEmpty(int row, int col) {
        return col > 0 && isTileEmpty(row, col - 1);
    }

    // TODO: return true/false depending whether the puzzle is solved or not (hint:
    // compare the values of current and original arrays)
    boolean isSolved() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (original[row][col] == null && current[row][col] == null) {
                    // matched
                } else {
                    if (original[row][col] == null && current[row][col] != null) {
                        return false;
                    } else if (original[row][col] != null && current[row][col] == null) {
                        return false;
                    } else if (!original[row][col].equals(current[row][col])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // TODO: perform the actual move of the tile by changing the current array
    // depending on the type of move; return the tile move (INVALID_MOVE,
    // MOVE_TILE_UP, MOVE_TILE_RIGHT, MOVE_TILE_DOWN, or MOVE_TILE_LEFT)
    int moveTile(int row, int col) {
        int move = getTileMove(row, col);
        BufferedImage tmp = current[row][col];

        if (move == MOVE_TILE_UP) {
            current[row][col] = current[row - 1][col];
            current[row - 1][col] = tmp;
        }
        if (move == MOVE_TILE_RIGHT) {
            current[row][col] = current[row][col + 1];
            current[row][col + 1] = tmp;
        }
        if (move == MOVE_TILE_LEFT) {
            current[row][col] = current[row][col - 1];
            current[row][col - 1] = tmp;
        }
        if (move == MOVE_TILE_DOWN) {
            current[row][col] = current[row + 1][col];
            current[row + 1][col] = tmp;
        }

        return move;
    }

    // nothing to do
    @Override
    public void paintComponent(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        for (int row = 0; row < GRID_SIZE; row++)
            for (int col = 0; col < GRID_SIZE; col++) {
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                if (current[row][col] != null)
                    g.drawImage(current[row][col], x, y, null);
                else
                    g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                g.drawRect(x, y, TILE_SIZE, TILE_SIZE);
            }
        g.setColor(color);
    }

    // nothing to do
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int row = y / TILE_SIZE;
        int col = x / TILE_SIZE;
        if (moveTile(row, col) != INVALID_MOVE) {
            repaint();
            if (isSolved()) {
                JOptionPane.showMessageDialog(null, "Puzzle Solved - Congratulations!", SlidingPuzzle.TITLE,
                        JOptionPane.INFORMATION_MESSAGE);
                removeMouseListener(this);
            }
        }
    }

    // nothing to do
    @Override
    public void mousePressed(MouseEvent e) {

    }

    // nothing to do
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    // nothing to do
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    // nothing to do
    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void setImageFilePrefix(String imageFilePrefix) {
        Puzzle.imageFilePrefix = imageFilePrefix;
    }

    public static void setImageFileSuffix(String imageFileSuffix) {
        Puzzle.imageFileSuffix = imageFileSuffix;
    }
}
