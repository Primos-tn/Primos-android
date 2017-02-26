
package pingo.mobile.com.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import pingo.mobile.com.R;
import pingo.mobile.com.ui.common.activities.HomeAcctivity;


public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    public AccessToken accessToken;
    private CallbackManager callbackManager;
    private String LOGIN_BUTTON_ID = "login";

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        Button button = (Button)findViewById(R.id.login);
        TextView textViewGoRegister = (TextView)findViewById(R.id.go_register);
        button.setOnClickListener(this);
        textViewGoRegister.setOnClickListener(this);
        // goHomeActivity();
        /*

// Test if the token is already valid

        accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            if (accessToken.isExpired() == false) {
                goHomeActivity();
            }

        } else {
            initLoginfb();
        }
*/
    }

    /**
     * Stop the base activity and start Home Activity
     */
    private void goHomeActivity() {
        Intent intent = new Intent(this, HomeAcctivity.class);
        startActivity(intent);
        finish();

    }

    /**
     * Prepare the callback register of loginButton on success we stop the base activity and start home activty
     */

    private void initLoginfb() {
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goHomeActivity();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button,
     * so long as we specify a parent activity in AndroidManifest.xml.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if (v.getId() == R.id.go_register) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
