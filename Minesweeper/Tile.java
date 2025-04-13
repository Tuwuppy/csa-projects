import processing.core.PApplet;
import processing.core.PConstants;

public class Tile {
    /** The clue for this tile -- related to all surrounding tiles */
    private int numberOfSurroundBombs;
    /** Whether or not this tile contains a mine */
    private boolean isMine;

    /** The state of this Tile, closed, open or flagged */
    private int state;
    private final static int CLOSED = 0;
    private final static int OPEN = 1;
    private final static int FLAGGED = 2;

    /**
     * CONSTRUCTOR
     */
    public Tile () {
        state = CLOSED;
    }

    /**
     * Opens this tile if it is CLOSED
     */
    public void open () {
        if (state == CLOSED)
            state = OPEN;
    }

    /**
     * Closed -> Flagged, and Flagged -> Closed
     */
    public void toggleFlag () {
        if (state == CLOSED)
            state = FLAGGED;
        else if (state == FLAGGED)
            state = CLOSED;
    }

    /**
     * True if this Tile is flagged
     * @return T if flagged, F otherwise
     */
    public boolean isFlagged () {
        return state == FLAGGED;
    }

    /**
     * True if this Tile contains a Mine
     * @return T if mined, F otherwise
     */
    public boolean isMine () {
        return isMine;
    }

    public void setMine () {
        isMine = true;
    }

    /**
     * True if this Tile is not opened
     * @return T if not opened, F otherwise
     */
    public boolean isClosed () {
        return state != OPEN;
    }

    /**
     * True if open
     * @return T if open, F otherwise
     */
    public boolean isOpen () {
        return state == OPEN;
    }

    public int getNumberOfSurroundBombs() {
        return numberOfSurroundBombs;
    }

    public void setNumberOfSurroundBombs (int number) {
        numberOfSurroundBombs = number;
    }

    /**
     * Draws this Tile at the specified location in the state of the Tile
     * @param p The PApplet to draw on
     * @param x The x location of the upper left corner of the tile
     * @param y The y location of the upper left corenr of the tile
     * @param size The height and width of the tile
     */
    public void draw (PApplet p, int x, int y, int size) {
        if (state == OPEN && isMine) {
            // draw a mine
            p.rect (x, y, size, size);
            p.fill(255,0,0);
            drawCenteredText(p, x, y, size, "X");
            p.fill(255);
        } else if (state == OPEN) {
            p.rect (x, y, size, size);
            p.fill(0);
            if (numberOfSurroundBombs == 0)
                drawCenteredText(p, x, y, size, "");
            else
                drawCenteredText(p, x, y, size, ""+numberOfSurroundBombs);
            p.fill(255);
        } else if (state == FLAGGED) {
            p.fill(155);
            p.rect (x, y, size, size);
            p.fill(255,255, 0);
            p.rect (x+20, y+5, size/2, size/3);
            p.rect(x+20, y+5, (size/4)-5, size-10);
            p.fill(255);
        } else {
            // draw a blank tile
            p.fill(155);
            p.rect(x, y, size, size);
            p.fill(255);
        }
    }
    private void drawCenteredText(PApplet p, int x, int y, int size, String text) {
        p.textSize(50);
        p.textAlign(PConstants.RIGHT);
        p.text(text, x + size-12, y + size-7);
        p.textAlign(PConstants.LEFT);
    }
}
