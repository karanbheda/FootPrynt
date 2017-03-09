package com.footprynt.footprynt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private LoginButton loginButton;
    private TwitterLoginButton btnLoginTwitter;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate_login);
        welcome = (TextView) findViewById(R.id.tv_welcome);
        alternate = (TextView) findViewById(R.id.tv_alternate);
        loginButton = (LoginButton) findViewById(R.id.fblogin);
        btnLoginTwitter = (TwitterLoginButton) findViewById(R.id.twtlogin);

        if(AccessToken.getCurrentAccessToken() == null) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            loginButton.setVisibility(View.VISIBLE);
            callbackManager = CallbackManager.Factory.create();
            loginButton.setReadPermissions(Arrays.asList(
                    "public_profile", "email", "user_friends"));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    startActivity(new Intent(AlternateLoginActivity.this,MainActivity.class));
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException e) {

                }
            });
        }
        else{
            TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
            Fabric.with(this, new Twitter(authConfig));
            btnLoginTwitter.setVisibility(View.VISIBLE);
            Callback twitterCallback = new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    startActivity(new Intent(AlternateLoginActivity.this,MainActivity.class));
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
}

