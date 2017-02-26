package pingo.mobile.com.utils.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by houssem.fathallah on 24/02/2017.
 */
public class Utils {
    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
