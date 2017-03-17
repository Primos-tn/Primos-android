package pingo.mobile.com.ui.common.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.api.responses.CategoriesApiResponse;
import pingo.mobile.com.stores.CategoriesStore;
import pingo.mobile.com.ui.home.activities.HomeActivity;
import pingo.mobile.com.utils.storage.Preferences;
import retrofit.RetrofitError;
import rx.Observer;

/**
 * Created by houssem.fathallah on 05/10/2016.
 */
public class StartApplicationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_application_activity);
        getServerInfo();
        ButterKnife.bind(this);
    }

    /**
     *
     * @param showError
     */
    public void toggleView(Boolean showError) {
        findViewById(R.id.loading_view).setVisibility(showError ? View.GONE : View.VISIBLE);
        findViewById(R.id.error_view).setVisibility(showError ? View.VISIBLE : View.GONE);
    }

    /**
     *
     */
    @OnClick(R.id.error_view_retry)
    public void getServerInfo() {
        toggleView(false);
        CategoriesStore.fetchCategories().subscribe(new Observer<CategoriesApiResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable cause) {
                try {
                    if (((RetrofitError) cause).getKind() == RetrofitError.Kind.NETWORK) {
                        toggleView(true);
                    }
                } catch (ClassCastException e) {

                }
            }

            @Override

            public void onNext(CategoriesApiResponse tags) {
                if (Preferences.getInstance(StartApplicationActivity.this).isLoggedIn()) {
                    startActivity(new Intent(StartApplicationActivity.this, HomeActivity.class));
                } else {
                    startActivity(new Intent(StartApplicationActivity.this, HomeActivity.class));
                }
                finish();
            }
        });
    }
}
