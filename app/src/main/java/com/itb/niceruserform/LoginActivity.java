package com.itb.niceruserform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText username, password;
    TextInputLayout usernameLayout, passwordLayout;
    MaterialButton login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);
        usernameLayout = findViewById(R.id.textInputLayoutUsername);
        passwordLayout = findViewById(R.id.textInputLayoutPassword);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        Intent intent = null;
        boolean aux = true;
        switch (v.getId()) {
            case R.id.register_button:
                intent = new Intent(this, RegisterActivity.class);
                break;
            case R.id.login_button:
                aux = camposIntroducidos();
                intent = new Intent(this, WelcomeActivity.class);
                break;
        }
        if (aux) startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean camposIntroducidos() {
        boolean aux = !Objects.requireNonNull(username.getText()).toString().equals("") && !Objects.requireNonNull(password.getText()).toString().equals("");
        if (!aux) {
            if (Objects.requireNonNull(username.getText()).toString().equals("")) {
                usernameLayout.setEnabled(true);
                usernameLayout.setError("Introduce el Username");
            }
            if (Objects.requireNonNull(password.getText()).toString().equals("")) {
                passwordLayout.setEnabled(true);
                passwordLayout.setError("Introduce el Password");
            }
        }
        return aux;
    }
}
