package nl.kingcrafting.snapclient.utils;

/**
 * Created by Jasper on 22-5-2017.
 */
public class Timer {
    private static long prevTime;

    public Timer() {
        prevTime = 0;
    }

    public static boolean hasTimePassed(long milSec) {
        return (float) (getTime() - prevTime) >= milSec;
    }

    public static void reset() {
        prevTime = getTime();
    }

    public static long getTime() {
        return System.nanoTime() / 1000000;
    }
}
