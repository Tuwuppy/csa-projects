import processing.core.PApplet;

/**
 * @TODO finish Tile
 */
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
     * @TODO should work now but revisit later just in case
     */
    public Tile (boolean isTileMine) {
        isMine = isTileMine;
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

    /**
     * @TODO make the mine art and number text here
     * Draws this Tile at the specified location in the state of the Tile
     * @param p The PApplet to draw on
     * @param x The x location of the upper left corner of the tile
     * @param y The y location of the upper left corenr of the tile
     * @param size The height and width of the tile
     */
    public void draw (PApplet p, int x, int y, int size) {
        if (state == OPEN && isMine) {
            // draw a mine
            // black circle? maybe a red X
            // @TODO implelement
            p.rect (x, y,size, size);
            p.fill(255);
            p.text("X", x + size, y + size);
        } else if (state == OPEN) {
            // @TODO implement
        } else if (state == FLAGGED) {
            // @TODO implement
        } else {
            // draw a blank tile
            p.rect (x, y,size, size);
        }
    }
}
