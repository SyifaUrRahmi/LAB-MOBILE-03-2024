package com.example.prak7iya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    EditText nimEditText, passwordEditText;
    TextInputLayout nimTextInputLayout, passwordTextInputLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nimEditText = findViewById(R.id.nim_login_et);
        passwordEditText = findViewById(R.id.password_login_et);

        nimTextInputLayout = findViewById(R.id.nim_login_til);
        passwordTextInputLayout = findViewById(R.id.password_login_til);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        checkLoginStatus();
    }

    public void onOpenRegisterButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void onLoginButtonClick(View view) {
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

        String storedNim = sharedPreferences.getString("NIM", null);
        String storedPassword = sharedPreferences.getString("Password", null);

        if (storedNim == null || storedPassword == null) {
            Toast.makeText(this, "No user data found. Register first!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nim.equals(storedNim) && password.equals(storedPassword)) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", true);
            editor.apply();
            // Proceed to the next activity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid NIM or Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkLoginStatus() {
        sharedPreferences = this.getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
