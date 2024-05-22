package com.example.prak7iya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    EditText nimEditText, passwordEditText;
    TextInputLayout nimTextInputLayout, passwordTextInputLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nimEditText = findViewById(R.id.nim_register_et);
        passwordEditText = findViewById(R.id.password_register_et);

        nimTextInputLayout = findViewById(R.id.nim_register_til);
        passwordTextInputLayout = findViewById(R.id.password_register_til);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
    }

    public void onRegisterButtonClick(View view) {
        nimTextInputLayout.setError(null);
        passwordTextInputLayout.setError(null);

        String nim = nimEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (nim.isEmpty()) {
            nimTextInputLayout.setError("NIM tidak boleh kosong");
        }

        if (password.isEmpty()) {
            passwordTextInputLayout.setError("Password tidak boleh kosong");
        }

        if (nim.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "NIM dan Password wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        editor = sharedPreferences.edit();
        editor.putString("NIM", nim);
        editor.putString("Password", password);
        editor.apply();

        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();

        // Redirect to LoginActivity
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
