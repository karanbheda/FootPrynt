package com.footprynt.footprynt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "swTQQ22LTLJO0Y26tM884YGZF";
    private static final String TWITTER_SECRET = "wEBRqbsj63J5S14ZynxmhbttHNqfMwMKvKjghoBdhQ3RPh8J7n";

    private LoginButton loginButton;
    private TwitterLoginButton btnLoginTwitter;
    private CallbackManager callbackManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md;

                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("hash key", something);
                Thread.sleep(200);
            }
        }
        catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            Log.e("name not found", e1.toString());
        }

        catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            Log.e("no such an algorithm", e.toString());
        }
        catch (Exception e){
            Log.e("exception", e.toString());
        }*/

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        FacebookSdk.sdkInitialize(getApplicationContext());
        sharedPreferences=getApplicationContext().getSharedPreferences("LOG", Context.MODE_PRIVATE);
        String abc=sharedPreferences.getString("abcxyz",null);
        if(abc!=null) {
            abc = abc.trim();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(isLogin()&&abc!=null){
            String pid= AccessToken.getCurrentAccessToken().getUserId();

            EncryptDecrypt encryptDecrypt=null;
            try {
                encryptDecrypt=new EncryptDecrypt("kthiksramAndroidDevs");
                String temp=encryptDecrypt.encrypt(pid);
                temp=temp.trim();
                if(temp.equals(abc.trim()))
                {
                    Toast.makeText(this, "HO GAYA", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.v("ogging out","okay"+this.getClass().getSimpleName());
                    LoginManager.getInstance().logOut();
                    final SharedPreferences tempSp= getSharedPreferences("NOTI",MODE_PRIVATE);
                    tempSp.edit().clear().apply();
                }
            } catch (Exception e) {
                e.printStackTrace();
                AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                alertbox.setTitle("Error");
                alertbox.setMessage("There was am error while decoding Login parameters\nPlease notify the developers of this");
                alertbox.show();
            }
        }
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.fb_login_button);
        btnLoginTwitter = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        Callback twitterCallback = new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        };
        btnLoginTwitter.setCallback(twitterCallback);

        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
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
        callbackManager.onActivityResult(requestCode, resultCode, data);
        btnLoginTwitter.onActivityResult(requestCode, resultCode, data);
    }

    public static boolean isLogin()
    {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
