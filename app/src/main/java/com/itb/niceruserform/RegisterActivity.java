package com.itb.niceruserform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText username, password, repeatPassword, email, name, surname;
    TextInputLayout usernameLayout, passwordLayout, repeatPasswordLayout, emailLayout, nameLayout, surnameLayout;
    Spinner gendre;
    MaterialButton login, register, date;
    boolean seRegistraCorrectamente;
    MaterialDatePicker materialDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        username = findViewById(R.id.editText_RegisterUsername);
        password = findViewById(R.id.editText_RegisterPassword);
        repeatPassword = findViewById(R.id.editText_RegisterRepeatPassword);
        email = findViewById(R.id.editText_RegisterEmail);
        name = findViewById(R.id.editText_RegisterName);
        surname = findViewById(R.id.editText_RegisterSurname);

        usernameLayout = findViewById(R.id.textInputLayout_RegisterUsername);
        passwordLayout = findViewById(R.id.textInputLayout_RegisterPassword);
        repeatPasswordLayout = findViewById(R.id.textInputLayout_RegisterRepeatPassword);
        emailLayout = findViewById(R.id.textInputLayout_RegisterEmail);
        nameLayout = findViewById(R.id.textInputLayout_RegisterName);
        surnameLayout = findViewById(R.id.textInputLayout_RegisterSurname);

        gendre = findViewById(R.id.spinner_RegisterGendre);
        login = findViewById(R.id.button_Login);
        register = findViewById(R.id.button_Register);
        date = findViewById(R.id.button_RegisterDate);

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("SELECT A DATE");
        materialDatePicker = materialDateBuilder.build();

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        date.setOnClickListener(this);

        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        Toast.makeText(RegisterActivity.this, "Fecha Seleccionada", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        Intent intent = null;
        boolean flag = false;
        switch (v.getId()) {
            case R.id.button_Login:
                intent = new Intent(this, LoginActivity.class);
                flag = true;
                break;
            case R.id.button_Register:
                intent = new Intent(this, WelcomeActivity.class);
                flag = seRegistraCorrectamente;
                comprobarSiSeRegistra();
                break;
            case R.id.button_RegisterDate:
                flag = false;
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
                break;
        }
        if (flag) startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void comprobarSiSeRegistra() {
        seRegistraCorrectamente = true;
        if (seHaModificadoEditText(username)) mostrarMensajeError(usernameLayout, "Introduce el Username");
        if (seHaModificadoEditText(password)) mostrarMensajeError(passwordLayout, "Introduce el Password");
        if (seHaModificadoEditText(repeatPassword)) mostrarMensajeError(repeatPasswordLayout, "Repite el Password");
        if (seHaModificadoEditText(email)) mostrarMensajeError(emailLayout, "Introduce el Email");
        if (seHaModificadoEditText(name)) mostrarMensajeError(nameLayout, "Introduce el Name");
        if (seHaModificadoEditText(surname)) mostrarMensajeError(surnameLayout, "Introduce el Surname");

        if (!Objects.requireNonNull(password.getText()).toString().equals(Objects.requireNonNull(repeatPassword.getText()).toString())) {
            seRegistraCorrectamente = false;
            passwordLayout.setEnabled(true);
            passwordLayout.setError("Las dos contraseñas han de coincidir");
            repeatPasswordLayout.setEnabled(true);
            repeatPasswordLayout.setError("Las dos contraseñas han de coincidir");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean seHaModificadoEditText(TextInputEditText editText) {return Objects.requireNonNull(editText.getText()).toString().equals("");}

    public void mostrarMensajeError(TextInputLayout layout, String message) {
        seRegistraCorrectamente = false;
        layout.setEnabled(true);
        layout.setError(message);
    }
}
