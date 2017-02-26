package pingo.mobile.com.ui.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import pingo.mobile.com.ui.account.LoginActivity;
import pingo.mobile.com.ui.user.activities.FavoritesActivity;

/**
 * Created by houssem.fathallah on 21/02/2017.
 */

public class Dialogs {
    public static void requireLogin(final Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login");
        alertDialog.setMessage("This interface require a login or account creation");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        context.startActivity(new Intent(context, LoginActivity.class));
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
