// @TODO STRETCH GOALS: first click always is a 0, variable board sizes and mine counts, pausing, add more later when i think of them

import processing.core.PApplet;
public class Main extends PApplet {

    /** The game of Minesweeper */
    private MineSweeper game;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    /**
     * The Processing SETUP
     * runs once at the start of the program
     */
    public void setup () {
        game = new MineSweeper();
    }

    /**
     * The Processing DRAW
     * Runs over and over and over
     * @TODO should work? but might not
     */
    public void draw () {
        game.draw (this);
    }

    /**
     * Processing SETTINGS
     * Set size of window here
     */
    public void settings () {
        size(1000,1000);
    }

    /**
     * The Processing KEYPRESSED
     * Run once when a key is pressed
     */
    public void keyPressed () {
        if (key == 'a')
            game.leftClick (mouseX, mouseY);
        if (key == 'd')
            game.rightClick (mouseX, mouseY);
        if (key == 'r')
            game.restart ();
    }

    /**
     * The Processing MOUSECLICKED
     * Run once when the mouse is clicked
     */
    public void mouseClicked () {
        if (mouseButton == LEFT)
            game.leftClick(mouseX, mouseY);
        if (mouseButton == RIGHT)
            game.rightClick (mouseX, mouseY);
    }
}
