package co.com.recuperacion_ciclo4_movil.mvp;

import android.app.Activity;

public interface LoginMVP {

    interface Model{

        void validateCredentials(String email, String password, ValidateCredentialsCallback callback);

        interface ValidateCredentialsCallback{
            void onSuccess();
            void onFailure(String error);
        }
    }

    interface Presenter{
        void LoginWithEmail();


    }

    interface View{
        Activity getActivity();
        LoginInfo getLoginInfo();
        void showEmailError(String error);
        void showPasswordError(String error);
        void showGeneralError(String error);

        void clearData();

        void openMainActivity();

        void startWaiting();

        void stopWaiting();
    }

    class LoginInfo {
        private final String email;
        private final String password;

        public LoginInfo(String email, String password){
            this.email = email;
            this.password = password;
        }

        public String getEmail(){
            return email;
        }

        public String getPassword(){
            return password;
        }

    }

}
