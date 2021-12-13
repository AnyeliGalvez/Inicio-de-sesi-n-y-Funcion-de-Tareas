package co.com.recuperacion_ciclo4_movil.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import co.com.recuperacion_ciclo4_movil.R;
import co.com.recuperacion_ciclo4_movil.mvp.LoginMVP;
import co.com.recuperacion_ciclo4_movil.presenter.LoginPresenter;
import co.com.recuperacion_ciclo4_movil.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {

    private final static String EMAIL_KEY = "email";
    private final static String PASSWORD_KEY = "password";

    private LinearProgressIndicator piWaiting;
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
        presenter.isLogged();

        initUI();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(EMAIL_KEY, etEmail.getText().toString());
        outState.putString(PASSWORD_KEY, etPassword.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        etEmail.setText(savedInstanceState.getString(EMAIL_KEY));
        etPassword.setText(savedInstanceState.getString(PASSWORD_KEY));
    }

    private void initUI() {

        piWaiting = findViewById(R.id.pi_waiting);

        RClogo  = findViewById(R.id.RClogo);

        tilEmail = findViewById(R.id.til_Email);
        etEmail = findViewById(R.id.et_Email);

        tilPassword = findViewById(R.id.til_password);
        etPassword = findViewById(R.id.et_password);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener((evt) -> presenter.LoginWithEmail());

    }


    @Override
    public Activity getActivity() {
        return this;
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

    @Override
    public void startWaiting() {
        piWaiting.setVisibility(View.VISIBLE);
        btn_login.setEnabled(false);
    }

    @Override
    public void stopWaiting() {
        piWaiting.setVisibility(View.GONE);
        btn_login.setEnabled(true);
    }
}