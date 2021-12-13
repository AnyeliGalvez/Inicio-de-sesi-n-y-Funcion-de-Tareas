package co.com.recuperacion_ciclo4_movil.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import co.com.recuperacion_ciclo4_movil.mvp.LoginMVP;

public class LoginInteractor implements LoginMVP.Model {

    private Map<String, String> users;

    public  LoginInteractor(){
        users = new HashMap<>();
        users.put("anyelimichell2011@gmail.com", "123456");
        users.put("cdiaz@email.com", "123456");
    }



    @Override
    public void validateCredentials(String email, String password, ValidateCredentialsCallback callback) {
        if(users.get(email) != null
                && Objects.equals(users.get(email), password)){
            callback.onSuccess();
        } else {
            callback.onFailure("Credenciales son inválidas");
        }
    }
}
