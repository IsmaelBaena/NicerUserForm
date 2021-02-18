package com.itb.niceruserform;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        register = findViewById(R.id.register_button);
        login = findViewById(R.id.login_button);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.register_button:
                intent = new Intent(this, RegisterActivity.class);
                break;
            case R.id.login_button:
                intent = new Intent(this, LoginActivity.class);
                break;
        }
        startActivity(intent);
    }
}