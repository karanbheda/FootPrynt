package com.footprynt.footprynt;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hanks.htextview.base.HTextView;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.Arrays;

import io.fabric.sdk.android.Fabric;

public class AlternateLoginActivity extends AppCompatActivity {
    private static final String TWITTER_KEY = "swTQQ22LTLJO0Y26tM884YGZF";
    private static final String TWITTER_SECRET = "wEBRqbsj63J5S14ZynxmhbttHNqfMwMKvKjghoBdhQ3RPh8J7n";

    private TextView welcome,alternate;
    private Button skip;
    private LoginButton loginButton;
    private TwitterLoginButton btnLoginTwitter;
    private CallbackManager callbackManager;
    private PrefManager prefManager;
    private SeekBar seekBar;
    int mCounter = 0;
    String[] fbtext = new String[]{"You can also connect to FootPrynt using Facebook?", "Earn more Miles by connecting to FaceBook"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate_login);
        welcome = (TextView) findViewById(R.id.tv_welcome);
        alternate = (TextView) findViewById(R.id.tv_alternate);
        loginButton = (LoginButton) findViewById(R.id.fblogin);
        btnLoginTwitter = (TwitterLoginButton) findViewById(R.id.twtlogin);
        skip = (Button) findViewById(R.id.btn_skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.setFirstTimeLaunch(false);
                startActivity(new Intent(AlternateLoginActivity.this, MainActivity.class));
                finish();
            }
        });
        TwitterSession twitterSession = Twitter.getSessionManager().getActiveSession();
        if(AccessToken.getCurrentAccessToken() == null) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            loginButton.setVisibility(View.VISIBLE);
            alternate.setText("You can now connect to FootPrynt via Facebook. Connect to FaceBook and earn more miles!");
            callbackManager = CallbackManager.Factory.create();
            loginButton.setReadPermissions(Arrays.asList(
                    "public_profile", "email", "user_friends"));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    prefManager.setFirstTimeLaunch(false);
                    startActivity(new Intent(AlternateLoginActivity.this,MainActivity.class));
                    finish();
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException e) {

                }
            });
        }
        else if(twitterSession == null){
            TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
            Fabric.with(this, new Twitter(authConfig));
            btnLoginTwitter.setVisibility(View.VISIBLE);
            alternate.setText("You can now connect to FootPrynt via Twitter. Connect to Twitter and earn more miles!");
            Callback twitterCallback = new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    prefManager.setFirstTimeLaunch(false);
                    startActivity(new Intent(AlternateLoginActivity.this,MainActivity.class));
                    finish();
                }

                @Override
                public void failure(TwitterException exception) {
                    // Do something on failure
                }
            };
            btnLoginTwitter.setCallback(twitterCallback);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        btnLoginTwitter.onActivityResult(requestCode, resultCode, data);
    }
    public static boolean isLogin()
    {

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    private void launchHomeScreen() {
        startActivity(new Intent(AlternateLoginActivity.this, MainActivity.class));
        finish();
    }
}

