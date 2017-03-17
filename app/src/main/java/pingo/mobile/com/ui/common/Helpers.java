package pingo.mobile.com.ui.common;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.Calendar;

import pingo.mobile.com.utils.constants.Intents;
import pingo.mobile.com.utils.services.ReminderService;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by houssem.fathallah on 21/02/2017.
 */

public class Helpers {
    /**
     * @param context
     */
    public static void createReminder(Context context, String productName) {
        Intent myIntent = new Intent(context, ReminderService.class);
        myIntent.putExtra(Intents.REMIND_TEXT_KEY, productName);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, myIntent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 61);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    /**
     *
     * @param apiToken
     * @return
     */
    public static String formatApiTokenForHeader (String apiToken) {
        return "Token token=\"" + apiToken + "\"";
    }
}
