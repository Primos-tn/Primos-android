
package pingo.mobile.com.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.api.models.accounts.PushRegistrationResponse;
import pingo.mobile.com.api.models.accounts.SignInResponse;
import pingo.mobile.com.stores.AccountsStore;
import pingo.mobile.com.ui.home.activities.HomeActivity;
import pingo.mobile.com.utils.fcm.PingoFireBaseInstanceIdService;
import pingo.mobile.com.utils.storage.Preferences;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

import static pingo.mobile.com.utils.constants.Bundles.PASS_PUSH_KEY_TOKEN;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LOGIN_RESPONSE";
    private CallbackManager callbackManager;

    @BindView(R.id.loading_progress)
    ProgressBar loader;

    @BindView(R.id.login_username)
    EditText usernameEditText;


    @BindView(R.id.login_password)
    EditText passwordEditText;

    @BindView(R.id.login_error_text)
    TextView errorLoginText;

    @BindView(R.id.login_button)
    Button loginButton;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /**
     *
     */
    @OnClick(R.id.go_register)
    void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.go_forgot_password)
    void forgetPassword() {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     *
     */
    @OnClick(R.id.login_button)
    void login() {
        String usernameValue = usernameEditText.getText().toString();
        String passwordValue = passwordEditText.getText().toString();
        if (usernameValue.length() < 4 && passwordValue.length() < 8) {
            return;
        }
        // hide it
        loginButton.setVisibility(View.INVISIBLE);
        loader.setVisibility(View.VISIBLE);
        errorLoginText.setVisibility(View.GONE);
        AccountsStore
                .login(usernameValue, passwordValue)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loader.setVisibility(View.GONE);
                        errorLoginText.setVisibility(View.VISIBLE);
                        loginButton.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(SignInResponse signInResponse) {
                        AccountsStore.setApiToken(getApplicationContext(), signInResponse);
                        attachPushTokenToUser();
                    }
                });
    }

    /**
     * Stop the base activity and start Home Activity
     */
    private void goHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Prepare the callback register of loginButton on success we stop the base activity and start home activty
     */

    private void loginWithFacebook() {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     *
     */
    public void openHomeActivity() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        Bundle b = new Bundle();
        b.putBoolean(PASS_PUSH_KEY_TOKEN, true); //Your id
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

    /**
     *
     */
    private void attachPushTokenToUser() {
        if (Preferences.getInstance(getApplicationContext()).getFireBasePushToken() != null ||
                PingoFireBaseInstanceIdService.getToken() != null
                ) {
            // attach userId with push token
            AccountsStore.attachUserToNotification(getApplicationContext())
                    .subscribe(new Observer<PushRegistrationResponse>() {
                        @Override
                        public void onCompleted() {
                            openHomeActivity();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(PushRegistrationResponse pushRegistrationResponse) {
                            Log.i(TAG, String.valueOf(pushRegistrationResponse.getIsOk()));

                        }
                    });
        }
        else {
            openHomeActivity();
        }


    }

    @Override
    public void onClick(View view) {

    }
}
