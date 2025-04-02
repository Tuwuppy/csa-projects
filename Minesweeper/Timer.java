import processing.core.PApplet;

/**
 * @TODO finish Timer
 */
public class Timer {

    /** The time that the Timer was started */
    long startTime;
    /** Whether or not the timer is currently running */
    boolean isRunning;

    /**
     * Stars the timer
     */
    public void start () {
        startTime = System.currentTimeMillis();  // the time in milliseconds
        isRunning = true;
    }

    /**
     * Stops the timer
     * @TODO make this stop the timer
     */
    public void stop () {
        isRunning = false;
    }

    /** @TODO make this actually work
     * Resets the timer to 0 and stops it
     */
    public void reset () {
        stop();
        start();
    }

    /** @TODO this maybe works but if timer is borked check this
     * Returns the current value of the timer
     * @return The number of seconds since the timer was started
     */
    public int read () {
        return (int) (System.currentTimeMillis()-startTime);
    }

    /** @TODO do this
     * Draws the current time at the specified location
     * @param p The PApplet to draw on
     * @param x The upper left X coordinate to draw at
     * @param y The upper left Y coordinate to draw at
     */
    public void draw (PApplet p, int x, int y) {
        p.fill (255,0,0);
        p.textSize (24);
        p.text ("99", x, y);
    }
}