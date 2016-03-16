package it.clicktoprofit.utility;

/**
 * Created by Gioele on 31/01/2016.
 */
public class Log {

    private static String message = "SERVER ERROR: ";

    public static void setMessage(String str) {
        message += str;
    }

    public static String getMessage() {
        return message;
    }

}
