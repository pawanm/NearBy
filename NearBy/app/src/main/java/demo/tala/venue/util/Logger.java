package demo.tala.venue.util;

import android.util.Log;

/**
 * Created by admin on 18/05/18.
 */

public class Logger {
    public static void log(String message) {
        Log.d("Venue", message);
    }

    public static void logE(String message) {
        Log.e("VenueError", message);
    }
}
