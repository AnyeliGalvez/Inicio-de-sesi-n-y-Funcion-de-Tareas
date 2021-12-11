package co.com.recuperacion_ciclo4_movil.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import co.com.recuperacion_ciclo4_movil.R;
import co.com.recuperacion_ciclo4_movil.view.MainActivity;

public class LoginActivity extends AppCompatActivity {
    ImageView RClogo;
    TextInputLayout tilEmail;
    TextInputEditText etEmail;
    TextInputLayout tilPassword;
    TextInputEditText etPassword;

    AppCompatButton btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    private void initUI() {
        RClogo  = findViewById(R.id.RClogo);

        tilEmail = findViewById(R.id.til_Email);
        etEmail = findViewById(R.id.et_Email);

        tilPassword = findViewById(R.id.til_password);
        etPassword = findViewById(R.id.et_password);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener((evt) -> {
            onLoginClick();
        });
    }

    private void onLoginClick(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "Inicio de sesión correcto.", Toast.LENGTH_SHORT).show();
    }
}