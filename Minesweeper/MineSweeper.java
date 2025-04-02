import processing.core.PApplet;
//@TODO finish Minesweeper
public class MineSweeper {
    /** The timer for a game of Minesweeper */
    private Timer timer;
    /** All of the tiles for a game of minesweeper */
    private Tile [][] grid;
    /** The number of flags remaining to theoretically cover all mines in the game */
    private int flagsRemaining;
    /** The number of mines for this game of minesweeper */
    private int numberOfMines;

    /**
     * CONSTRUCTOR: Sets all the instance variables
     */
    public MineSweeper () {
        //@TODO revisit constructor, it might need to be redone
        timer = new Timer();
        numberOfMines = 10;
        grid = regenTiles(numberOfMines);
    }

    /** Helper method
     * Fills the 2d array grid with tiles
     * @TODO this only makes nonmines as a temp, so implement the mines
     */
    private Tile[][] regenTiles(int mineCount) {
        Tile[][] temp = new Tile[10][10];
        for (int r = 0 ; r < temp.length ; r++) {
            for (int c = 0 ; c < temp[r].length ; c++) {
                temp[r][c] = new Tile(false);
            }
        }
        // TEMP ADDING SPECIFIC MINES
        // @TODO remove this
        temp[0][0].setMine();
        temp[0][0].open();

        /* add mines
        pick a random r and c, make sure there's not a mine already there, and set that tile to a mine
        repeat x times where x is the number of mines you want
         */



        return temp;
    }

    /** @TODO maybe works but check back later
     * Restarts the game of Minesweeper
     */
    public void restart () {
        numberOfMines = 10;
        grid = regenTiles(numberOfMines);
        timer.reset();
    }

    /** @TODO draw the final 3 objects here
     * Draws the current state of MineSweeper to the PApplet p
     * @param p The PApplet to display the game on
     */
    public void draw (PApplet p) {
        // go thru r and c to draw each tile in the grid
        // @TODO finalize position of tiles
        int tileX = 100;
        int tileY = 300;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c].draw(p, tileX, tileY, 50);
                tileX+=50;
            }
            tileX = 100;
            tileY+=50;
        }
        // draw timer

        //draw flag counter

        // draw restart button
    }

    /** @TODO make this work
     * HELPER FUCTION: to indicate if the game is over
     * @return True if there are any revealed mines, or if all non-mines are revealed
     * False otherwise
     */
    private boolean isGameOver () {
        return false;
    }

    /** @TODO make this
     * Processes a left click by the user at the location x,y
     * Should open a tile, or reset the game, or sweep a valid open location
     * @param x X coordinate mouse location
     * @param y Y coordinate mouse location
     */
    public void leftClick (int x, int y) {

    }

    /** @TODO make this
     * Processes a right click by the user at the location x,y
     * Should flag/unflag a tile if possible
     * @param x X coordinate mouse location
     * @param y Y coordinate mouse location
     */
    public void rightClick (int x, int y) {

    }

}
