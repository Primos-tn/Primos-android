package pingo.mobile.com.ui.common;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

import pingo.mobile.com.utils.constants.Intents;
import pingo.mobile.com.utils.services.ReminderService;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by houssem.fathallah on 21/02/2017.
 */

public class Toasts {
    /**
     * @param context
     */
    public static void createShort(Context context, String toastString) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, toastString, duration);
        toast.show();
    }
}
