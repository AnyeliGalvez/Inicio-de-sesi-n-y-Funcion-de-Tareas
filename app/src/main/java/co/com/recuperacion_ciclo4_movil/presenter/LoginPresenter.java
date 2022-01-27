package co.com.recuperacion_ciclo4_movil.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import co.com.recuperacion_ciclo4_movil.model.LoginInteractor;
import co.com.recuperacion_ciclo4_movil.mvp.LoginMVP;
import co.com.recuperacion_ciclo4_movil.view.LoginActivity;
import co.com.recuperacion_ciclo4_movil.view.MainActivity;

public class LoginPresenter implements LoginMVP.Presenter {

    private final static String AUTH_PREFERENCES = "authentication";
    private final static String LOGGED = "logged";

    private LoginMVP.View view;
    private LoginMVP.Model model;

    public LoginPresenter(LoginMVP.View view){
        this.view = view;
        this.model = new LoginInteractor();
    }

    /*@Override
    public void isLogged() {
        SharedPreferences preferences = view.getActivity()
                .getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
        boolean isLogged = preferences.getBoolean(LOGGED, false);
        if(isLogged) {
            view.openMainActivity();

        }


    }*/


    @Override
    public void LoginWithEmail() {
        boolean error = false;

        view.showEmailError("");
        view.showPasswordError("");

        LoginMVP.LoginInfo loginInfo = view.getLoginInfo();
        if(loginInfo.getEmail().trim().isEmpty()){
            view.showEmailError("Correo electrónico obligatorio.");
            error = true;
        } else if(!isEmailValid(loginInfo.getEmail().trim())) {
            view.showEmailError("Correo electrónico no válido.");
            error = true;
        }
        if(loginInfo.getPassword().trim().isEmpty()){
            view.showPasswordError("Contraseña obligatoria.");
            error = true;
        } else if(!isPasswordValid(loginInfo.getPassword().trim())) {
            view.showPasswordError("Contraseña debe contener mínimo 6 caracteres.");
            error = true;
        }

        if(!error) {
            view.startWaiting();
            model.validateCredentials(loginInfo.getEmail().trim(),
                    loginInfo.getPassword().trim(), new LoginMVP.Model.ValidateCredentialsCallback() {
                        @Override
                        public void onSuccess() {
                           /* SharedPreferences preferences = view.getActivity()
                                    .getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
                            preferences.edit()
                                    .putBoolean(LOGGED, true)
                                    .apply();*/

                            view.getActivity().runOnUiThread(() -> {
                                view.stopWaiting();
                                view.openMainActivity();
                            });


                        }

                        @Override
                        public void onFailure(String error) {
                            view.getActivity().runOnUiThread(() ->{
                                view.showGeneralError(error);
                                view.stopWaiting();
                            });

                        }
                    });
        }
    }




    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }


    private boolean isEmailValid(String email) {
        return email.contains("@") && email.endsWith(".com");
    }
}
