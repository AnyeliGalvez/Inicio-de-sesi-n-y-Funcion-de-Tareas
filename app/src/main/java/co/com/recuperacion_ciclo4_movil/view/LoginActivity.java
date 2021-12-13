package co.com.recuperacion_ciclo4_movil.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import co.com.recuperacion_ciclo4_movil.R;
import co.com.recuperacion_ciclo4_movil.mvp.LoginMVP;
import co.com.recuperacion_ciclo4_movil.presenter.LoginPresenter;
import co.com.recuperacion_ciclo4_movil.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {
    private ImageView RClogo;
    private TextInputLayout tilEmail;
    private TextInputEditText etEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText etPassword;

    private AppCompatButton btn_login;

    private LoginMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        initUI();
    }

    private void initUI() {
        RClogo  = findViewById(R.id.RClogo);

        tilEmail = findViewById(R.id.til_Email);
        etEmail = findViewById(R.id.et_Email);

        tilPassword = findViewById(R.id.til_password);
        etPassword = findViewById(R.id.et_password);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener((evt) -> presenter.LoginWithEmail());

    }



    @Override
    public LoginMVP.LoginInfo getLoginInfo() {
        return new LoginMVP.LoginInfo(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void showEmailError(String error) {
        tilEmail.setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        tilPassword.setError(error);
    }

    @Override
    public void showGeneralError(String error) {
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearData() {
        tilEmail.setError("");
        etEmail.setText("");
        tilPassword.setError("");
        etPassword.setText("");
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}