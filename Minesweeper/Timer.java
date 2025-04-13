import processing.core.PApplet;

public class Timer {

    /** The time that the Timer was started */
    private long startTime;
    /** Whether or not the timer is currently running */
    private boolean isRunning;
    /** The time that the Timer was stopped */
    private long stopTime;

    /**
     * Starts the timer
     */
    public void start () {
        startTime = System.currentTimeMillis();  // the time in milliseconds
        isRunning = true;
    }

    /**
     * Stops the timer
     */
    public void stop () {
        isRunning = false;
        stopTime = System.currentTimeMillis();
    }

    /**
     * Resets the timer to 0 and stops it
     */
    public void reset () {
        stop();
        startTime = stopTime;
    }

    /**
     * Returns the current value of the timer
     * @return The number of seconds since the timer was started
     * The divide by 1000 turns milliseconds into normal seconds
     */
    public int read () {
        if (!isRunning)
            return (int) (stopTime-startTime) / 1000;
        return (int) (System.currentTimeMillis()-startTime) / 1000;
    }

    public boolean isRunning () {
        return isRunning;
    }

    /**
     * Draws the current time at the specified location
     * @param p The PApplet to draw on
     * @param x The upper left X coordinate to draw at
     * @param y The upper left Y coordinate to draw at
     */
    public void draw (PApplet p, int x, int y) {
        p.fill (255);
        p.rect(60,60,150,60);
        p.fill(0);
        p.textSize (24);
        p.text (read(), x, y);
        p.fill(255);
    }
}
