package pingo.mobile.com.ui.home.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import pingo.mobile.com.R;

import pingo.mobile.com.utils.constants.Application;


/**
 * Created by houssem.fathallah on 23/02/2017.
 */
public class AboutUsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setLink(R.id.link_to_business, Application.BUSINESS_URL);
        setLink(R.id.link_to_icon8, Application.ICON8_HTML_LINK);

    }

    void setLink(int id, String text) {
        TextView link;
        link = (TextView) findViewById(id);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            link.setText(Html.fromHtml(text, 0));
        } else {
            link.setText(Html.fromHtml(text));
        }
    }
}
