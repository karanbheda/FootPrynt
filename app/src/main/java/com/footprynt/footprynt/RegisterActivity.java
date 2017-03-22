package com.footprynt.footprynt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button) findViewById(R.id.btn_Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, AlternateLoginActivity.class));
                finish();
                finish();
            }
        });
    }

    private void launchHomeScreen() {
        startActivity(new Intent(RegisterActivity.this, AlternateLoginActivity.class));
        finish();
    }

}