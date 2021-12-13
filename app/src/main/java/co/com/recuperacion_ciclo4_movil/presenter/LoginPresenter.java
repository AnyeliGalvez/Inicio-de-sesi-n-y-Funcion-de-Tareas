package co.com.recuperacion_ciclo4_movil.presenter;

import android.content.Intent;
import android.util.Log;

import co.com.recuperacion_ciclo4_movil.model.LoginInteractor;
import co.com.recuperacion_ciclo4_movil.mvp.LoginMVP;

public class LoginPresenter implements LoginMVP.Presenter {

    private LoginMVP.View view;
    private LoginMVP.Model model;

    public LoginPresenter(LoginMVP.View view){
        this.view = view;
        this.model = new LoginInteractor();
    }

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
