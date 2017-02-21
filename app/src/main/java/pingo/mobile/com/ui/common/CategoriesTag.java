package pingo.mobile.com.ui.common;

import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.greenfrvr.hashtagview.HashtagView;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Category;

/**
 * Created by houssem.fathallah on 21/02/2017.
 */

public class CategoriesTag {
    /**
     *
     */
    public static HashtagView.DataStateTransform<Category> getTagsTransformer(final Context context) {
        return new HashtagView.DataStateTransform<Category>() {
            @Override
            public CharSequence prepare(Category item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.ColorPrimary, context.getTheme())), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else {
                    spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.ColorPrimary)), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                return spannableString;
            }

            @Override
            public CharSequence prepareSelected(Category item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.WhiteColor, context.getTheme())), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                } else {
                    spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.WhiteColor)), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }
                return spannableString;
            }
        };
    }
}
