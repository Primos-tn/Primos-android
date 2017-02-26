package pingo.mobile.com.utils.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import pingo.mobile.com.utils.constants.Intents;
import pingo.mobile.com.utils.services.ReminderService;


public class ReminderBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startIntent = new Intent(context, ReminderService.class);
        String received = intent.getStringExtra(Intents.REMIND_TEXT_KEY);
        startIntent.putExtra(Intents.REMIND_TEXT_KEY, received);
        context.startService(startIntent);
    }

}