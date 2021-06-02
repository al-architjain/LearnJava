package game;
import static game.GameTileValue._2048;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    public static final int DIM = 4;
    public static final int[] _0123 = {0,1,2,3};

    final AppFrame hostGame;
    private GameTile[] boardTiles;
    public static GameTileValue GOAL = _2048;

    public GamePanel (AppFrame game) {
        hostGame = game;
        setFocusable(true);
        initBoardTiles();
    }

    public void initBoardTiles() {
        boardTiles = new GameTile[DIM*DIM];
        for (int i = 0; i < boardTiles.length; i++) {
            boardTiles[i] = GameTile.ZERO;
        }
        addNewTile();
        addNewTile();
        hostGame.clearStatusBar();
    }

    // Add a new tile to a random place.
    private void addNewTile() {
        List<Integer> availableIndexes = getAvailableIndex();
        int index = availableIndexes.get((int) (Math.random() * availableIndexes.size()));
        boardTiles[index] = GameTile.randomTile();
    }

    private List<Integer> getAvailableIndex() {
        List<Integer> availableIndexes = new LinkedList<>();
        for (int i = 0; i<boardTiles.length; i++ ) {
            if (boardTiles[i].empty())  availableIndexes.add(i);
        }
        return availableIndexes;
    }

    public void moveLeft() {
        computeMoveLeft();
    }

    public void moveDown() {
        rotateTiles(90);
        computeMoveLeft();
        rotateTiles(270);
    }

    public void moveRight() {
        rotateTiles(180);
        computeMoveLeft();
        rotateTiles(180);
    }

    public void moveUp() {
        rotateTiles(270);
        computeMoveLeft();
        rotateTiles(90);
    }

    public void computeMoveLeft() {
        boolean needAddTile = false;
        for (int i: _0123) {
            GameTile[] original = getLine(i);
            GameTile[] newLine = shiftLeft(original);
            setLine(i, newLine);
            if (!needAddTile && !Arrays.equals(original, newLine)) {
                needAddTile = true;
            }
        }
        if (needAddTile){
            addNewTile();
        }
    }

    private GameTile[] shiftLeft(GameTile[] oldLine) {
        GameTile[] newLine = new GameTile[4];
        for (int i: _0123) newLine[i] = GameTile.ZERO;
        
        int i = 0, j = 0;
        GameTile prev = GameTile.ZERO;
        while(i < DIM){
            if (oldLine[i].empty()) {
                i++;
            }
            else if (oldLine[i].equals(prev)) {
                newLine[j] = oldLine[i].getDoubleTile();
                if (newLine[j].getValue() == GOAL) {
                    hostGame.win();
                }
                prev = GameTile.ZERO; 
                j++; i++;
            }
            else {
                if (prev != GameTile.ZERO){
                    newLine[j] = prev;
                    j++;
                }
                prev = oldLine[i];
                i++;
            }
        }
        newLine[j] = prev;
        
        return newLine;
    }
    
    private GameTile[] getLine(int index) {
        GameTile[] result = new GameTile[4];
        for (int i : _0123) {
            result[i] = getTileAt(i, index);
        }
        return result;
    }

    private void setLine(int index, GameTile[] newLine) {
        for (int i : _0123) {
            boardTiles[i + index * DIM] = newLine[i];
        }
    }

    private void rotateTiles(int degree) {
        GameTile[] newTiles = new GameTile[DIM*DIM];
        int offsetX = 0, offsetY = 0;
        if (degree == 90) {
            offsetX = 3;
        }
        else if (degree == 180) {
            offsetX = 3;
            offsetY = 3;
        }
        else { // degree == 270;
            offsetY = 3;
        }
        // Understand this portion:
        double radians = Math.toRadians(degree);
        int cos = (int) Math.cos(radians);
        int sin = (int) Math.sin(radians);
        for (int x : _0123) {
            for (int y: _0123) {
                int newX = (x * cos) - (y * sin) + offsetX;
                int newY = (x * sin) + (y * cos) + offsetY;
                newTiles[(newX) + (newY)*DIM] = getTileAt(x,y);
            }
        }
        boardTiles = newTiles;
    }

    private GameTile getTileAt(int x, int y) {
        return boardTiles[x + y * DIM];
    }

    private boolean isFull() {
        return getAvailableIndex().size() == 0;
    }

    boolean canMove() {
        if (!isFull()) {
            return true;
        }
        for (int x : _0123) {
            for (int y : _0123) {
                GameTile t = getTileAt(x, y);
                if ((x < DIM - 1 && t.equals(getTileAt(x + 1, y)))
                        || (y < DIM - 1 && t.equals(getTileAt(x, y + 1)))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final Color BG_COLOR = new Color(0x282C31);
    private static final Font STR_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 17);

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.setFont(STR_FONT);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int y: _0123) {
            for (int x: _0123) {
                drawTile(g, boardTiles[x + y * DIM], x, y);
            }
        }
    }

    private static final int SIDE = 64;
    private static final int MARGIN = 16;

    private void drawTile(Graphics g, GameTile tile, int x, int y) {
        GameTileValue val = tile.getValue();
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        g.setColor(val.getColor());
        g.fillRect(xOffset, yOffset, SIDE, SIDE);
        g.setColor(val.getFontColor());
        if (val.getValue() != 0)
            g.drawString(tile.toString(), xOffset
                    + (SIDE >> 1) - MARGIN, yOffset + (SIDE >> 1));
    }

    private static int offsetCoors(int arg) {
        return arg * (MARGIN + SIDE) + MARGIN;
    }
}
