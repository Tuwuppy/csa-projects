import processing.core.PApplet;
public class MineSweeper {
    /** The timer for a game of Minesweeper */
    private Timer timer;
    /** All of the tiles for a game of minesweeper */
    private Tile [][] grid;
    /** The number of mines for this game of minesweeper */
    private int numberOfMines;
    /** if the click is the first click in the game */
    private boolean isFirstClick;

    /**
     * CONSTRUCTOR: Sets all the instance variables
     */
    public MineSweeper () {
        timer = new Timer();
        numberOfMines = 10;
        // @TODO change this to a variable board length
        grid = new Tile[10][10];
        grid = regenTiles(numberOfMines);
    }

    /** Helper method
     * Fills the 2d array grid with tiles
     */
    private Tile[][] regenTiles(int mineCount) {
        isFirstClick = true;
        for (int r = 0 ; r < grid.length ; r++) {
            for (int c = 0 ; c < grid[r].length ; c++) {
                grid[r][c] = new Tile();
            }
        }

        /* add mines
        pick a random r and c, make sure there's not a mine already there, and set that tile to a mine
        repeat x times where x is the number of mines you want
         */
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int mineR = (int) (Math.random() * 10);
            int mineC = (int) (Math.random() * 10);
            if (!grid[mineR][mineC].isMine()) {
                grid[mineR][mineC].setNumberOfSurroundBombs(-1);
                grid[mineR][mineC].setMine();
                minesPlaced++;
            }
        }
        // make the tiles have the right hints
        for (int r = 0 ; r < grid.length ; r++) {
            for (int c = 0 ; c < grid[r].length ; c++) {
                int numOfMines = 0;
                // top row
                if (isValid(r-1, c-1)) {
                    if (grid[r-1][c-1].isMine())
                        numOfMines++;
                }
                if (isValid(r-1, c)) {
                    if (grid[r-1][c].isMine())
                        numOfMines++;
                }
                if (isValid(r-1, c+1)) {
                    if (grid[r-1][c+1].isMine())
                        numOfMines++;
                }
                // same row
                if (isValid(r, c-1)) {
                    if (grid[r][c-1].isMine())
                        numOfMines++;
                }
                if (isValid(r, c+1)) {
                    if (grid[r][c+1].isMine())
                        numOfMines++;
                }
                // bottom row
                if (isValid(r+1, c-1)) {
                    if (grid[r+1][c-1].isMine())
                        numOfMines++;
                }
                if (isValid(r+1, c)) {
                    if (grid[r+1][c].isMine())
                        numOfMines++;
                }
                if (isValid(r+1, c+1)) {
                    if (grid[r+1][c+1].isMine())
                        numOfMines++;
                }
                grid[r][c].setNumberOfSurroundBombs(numOfMines);
            }
        }

        return grid;
    }

    /** @TODO make this work with variable board sizes
     * Restarts the game of Minesweeper
     */
    public void restart () {
        numberOfMines = 10;
        grid = regenTiles(numberOfMines);
        timer.reset();
    }

    /**
     * Draws the current state of MineSweeper to the PApplet p
     * @param p The PApplet to display the game on
     */
    public void draw (PApplet p) {
        p.background (200);
        // go thru r and c to draw each tile in the grid
        int tileX = 250;
        int tileY = 300;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c].draw(p, tileX, tileY, 50);
                tileX+=50;
            }
            tileX=250;
            tileY+=50;
        }
        // draw timer
        timer.draw(p, 150, 100);
        //draw flag counter
        p.fill(255);
        p.rect(300, 50, 230, 75);
        p.fill(0);
        p.text(numberOfMines-countFlags() + " bombs remaining", 310, 100);
        p.fill(255);
        // draw restart button
        p.fill(255);
        p.rect(700, 50, 150, 50);
        p.fill(0);
        p.text("Restart", 750, 75);
        p.fill(255);
        // check if game is over constantly and draw text if it is
        p.fill(255);
        p.rect(400, 175, 200, 50);
        if (isGameOver().equals("win")) {
            p.fill(0);
            p.text("You Win!", 465, 205);
            p.fill(255);
        } else if (isGameOver().equals("lose")) {
            p.fill(0);
            p.text("You Lose :(", 455, 205);
            p.fill(255);
        }
    }

    private int countFlags() {
        int flags = 0;
        for (Tile[] temp : grid) {
            for (Tile tile : temp) {
                if (tile.isFlagged())
                    flags++;
            }
        }
        return flags;
    }

    /**
     * HELPER FUCTION: to indicate if the game is over
     * @return True if there are any revealed mines, or if all non-mines are revealed
     * False otherwise
     */
    private String isGameOver () {
        int revealedCount = 0;
        for (Tile[] temp : grid) {
            for (Tile tile : temp) {
                if (tile.isOpen() && tile.isMine()) {
                    if (timer.isRunning())
                        timer.stop();
                    return "lose";
                } else if (tile.isOpen()) {
                    revealedCount++;
                }
            }
        }
        if ((grid.length * grid[0].length) - revealedCount == numberOfMines && revealedCount == (grid.length * grid[0].length) - numberOfMines) {
            if (timer.isRunning())
                timer.stop();
            return "win";
        }

        return "not over";
    }

    /** If opened tile is a 0, instead call this to open all the zeroes around that zero and the ones around that one and so on
     *
     */
    public void recursiveOpen(int zeroR, int zeroC) {
        if (isValid(zeroR-1, zeroC-1)) {
            if (grid[zeroR-1][zeroC-1].isClosed()) {
                if (grid[zeroR - 1][zeroC - 1].getNumberOfSurroundBombs() == 0) {
                    grid[zeroR - 1][zeroC - 1].open();
                    recursiveOpen(zeroR - 1, zeroC - 1);
                } else
                    grid[zeroR - 1][zeroC - 1].open();
            }
        }
        if (isValid(zeroR-1, zeroC)) {
            if (grid[zeroR-1][zeroC].isClosed()) {
                if (grid[zeroR - 1][zeroC].getNumberOfSurroundBombs() == 0) {
                    grid[zeroR - 1][zeroC].open();
                    recursiveOpen(zeroR - 1, zeroC);
                } else
                    grid[zeroR - 1][zeroC].open();
            }
        }
        if (isValid(zeroR-1, zeroC+1)) {
            if (grid[zeroR-1][zeroC+1].isClosed()) {
                if (grid[zeroR - 1][zeroC + 1].getNumberOfSurroundBombs() == 0) {
                    grid[zeroR - 1][zeroC + 1].open();
                    recursiveOpen(zeroR - 1, zeroC + 1);
                } else
                    grid[zeroR - 1][zeroC + 1].open();
            }
        }
        // same row
        if (isValid(zeroR, zeroC-1)) {
            if (grid[zeroR][zeroC-1].isClosed()) {
                if (grid[zeroR][zeroC - 1].getNumberOfSurroundBombs() == 0) {
                    grid[zeroR][zeroC - 1].open();
                    recursiveOpen(zeroR, zeroC - 1);
                } else
                    grid[zeroR][zeroC - 1].open();
            }
        }
        if (isValid(zeroR, zeroC+1)) {
            if (grid[zeroR][zeroC+1].isClosed()) {
                if (grid[zeroR][zeroC + 1].getNumberOfSurroundBombs() == 0) {
                    grid[zeroR][zeroC + 1].open();
                    recursiveOpen(zeroR, zeroC + 1);
                } else
                    grid[zeroR][zeroC + 1].open();
            }
        }
        // bottom row
        if (isValid(zeroR+1, zeroC-1)) {
            if (grid[zeroR+1][zeroC-1].isClosed()) {
                if (grid[zeroR + 1][zeroC - 1].getNumberOfSurroundBombs() == 0) {
                    grid[zeroR + 1][zeroC - 1].open();
                    recursiveOpen(zeroR + 1, zeroC - 1);
                } else
                    grid[zeroR + 1][zeroC - 1].open();
            }
        }
        if (isValid(zeroR+1, zeroC)) {
            if (grid[zeroR+1][zeroC].isClosed()) {
                if (grid[zeroR + 1][zeroC].getNumberOfSurroundBombs() == 0) {
                    grid[zeroR + 1][zeroC].open();
                    recursiveOpen(zeroR + 1, zeroC);
                } else
                    grid[zeroR + 1][zeroC].open();
            }
        }
        if (isValid(zeroR+1, zeroC+1)) {
            if (grid[zeroR+1][zeroC+1].isClosed()) {
                if (grid[zeroR + 1][zeroC + 1].getNumberOfSurroundBombs() == 0) {
                    grid[zeroR + 1][zeroC + 1].open();
                    recursiveOpen(zeroR + 1, zeroC + 1);
                } else
                    grid[zeroR + 1][zeroC + 1].open();
            }
        }
        if (grid[zeroR][zeroC].isClosed()) {
            grid[zeroR][zeroC].open();
        }
    }
    // @TODO make an x to r and y to c function
    /** @TODO make this work with variable board sizes
     * Processes a left click by the user at the location x,y
     * Should open a tile, or reset the game, or sweep a valid open location
     * @param x X coordinate mouse location
     * @param y Y coordinate mouse location
     */
    public void leftClick (int x, int y) {
        // bounds of the grid
        if (x >= 250 && x <= 750 && y >= 300 && y <= 800) {
            int tileR = (y-300)/50;
            int tileC = (x-250)/50;
            if (isFirstClick) {
                timer.start();
                isFirstClick = false;
            }
            if (grid[tileR][tileC].getNumberOfSurroundBombs() == 0) {
                recursiveOpen(tileR, tileC);
            } else {
                grid[tileR][tileC].open();
            }
        } else if (x <= 850 && x >= 700 && y<= 100 && y >= 50) {
            restart();
        }
    }

    /**
     * Processes a right click by the user at the location x,y
     * Should flag/unflag a tile if possible
     * @param x X coordinate mouse location
     * @param y Y coordinate mouse location
     */
    public void rightClick (int x, int y) {
        // bounds of grid
        if (x >= 250 && x <= 750 && y >= 300 && y <= 800) {
            int tileR = (y - 300) / 50;
            int tileC = (x - 250) / 50;
            grid[tileR][tileC].toggleFlag();
        }
    }

    public boolean isValid (int r, int c) {
        return r >= 0 && c >= 0 && r < grid.length && c < grid[r].length && !grid[r][c].isFlagged();
    }

}
