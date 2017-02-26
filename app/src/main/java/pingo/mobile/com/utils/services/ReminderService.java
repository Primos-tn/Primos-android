package pingo.mobile.com.utils.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import pingo.mobile.com.R;
import pingo.mobile.com.utils.constants.Application;
import pingo.mobile.com.utils.constants.Intents;

import static pingo.mobile.com.utils.constants.Application.APP_NAME;

/**
 * Created by houssem.fathallah on 25/02/2017.
 */
public class ReminderService extends Service {
    String text;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        text = (String) intent.getExtras().get(Intents.REMIND_TEXT_KEY);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_icon)
                        .setContentTitle(APP_NAME)
                        .setContentText(text);


// Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

//When you issue multiple notifications about the same type of event,
// it’s best practice for your app to try to update an existing notification with this new information,
// rather than immediately creating a new notification. If you want to update this notification at a later date, you need to assign it an ID.
// You can then use this ID whenever you issue a subsequent notification.
// If the previous notification is still visible, the system will update this existing notification, rather than create a new one.
// In this example, the notification’s ID is 001//

        mNotificationManager.notify(001, mBuilder.build());
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
