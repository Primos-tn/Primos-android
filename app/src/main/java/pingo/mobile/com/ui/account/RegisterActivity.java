
package pingo.mobile.com.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import pingo.mobile.com.R;
import pingo.mobile.com.ui.common.activities.HomeAcctivity;
import pingo.mobile.com.api.models.accounts.SignUpResponse;
import pingo.mobile.com.stores.AccountsStore;
import rx.Observer;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private CallbackManager callbackManager;
    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordConfirmationEditText;
    EditText ageEditText;
    private String LOGIN_BUTTON_ID = "register";

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.register_fragment);
        Button button = (Button) findViewById(R.id.register_register_button);
        button.setOnClickListener(this);
        TextView textViewGoLogin = (TextView) findViewById(R.id.go_login);
        textViewGoLogin.setOnClickListener(this);

        usernameEditText = (EditText) findViewById(R.id.register_username_edit_text);
        emailEditText = (EditText) findViewById(R.id.register_email_edit_text);
        ageEditText = (EditText) findViewById(R.id.register_age_edit_text);
        passwordEditText = (EditText) findViewById(R.id.register_password_edit_text);
        passwordConfirmationEditText = (EditText) findViewById(R.id.register_password_confirmation_edit_text);
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
     *
     */
    private void register() {
        String usernameValue = usernameEditText.getText().toString();
        String emailValue = emailEditText.getText().toString();
        int ageValue = Integer.parseInt(ageEditText.getText().toString());
        String passwordValue = passwordEditText.getText().toString();
        String passwordConfirmationValue = passwordConfirmationEditText.getText().toString();
        if (usernameValue.length() < 5 || emailValue.length() < 100 || passwordValue.length() < 7 || passwordConfirmationValue.length() < 7) {
            return;
        }
        if (!passwordConfirmationValue.equals(passwordValue)) {
            return;
        }
        AccountsStore
                .register(usernameValue, emailValue, passwordValue, passwordConfirmationValue, ageValue)
                .subscribe(new Observer<SignUpResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SignUpResponse signUpResponse) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    /**
     * Prepare the callback register of loginButton on success we stop the base activity and start home activty
     */

    private void initFacebookRegister() {
        callbackManager = CallbackManager.Factory.create();
        LoginButton registerButton = (LoginButton) findViewById(R.id.login_button);
        registerButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        /**
         *
         */
        if (v.getId() == R.id.register_register_button) {
            register();
        } else if (v.getId() == R.id.go_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
