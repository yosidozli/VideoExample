package com.yosidozli.videoexample.ui.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.yosidozli.videoexample.R;
import com.yosidozli.videoexample.data.LoginRepository;
import com.yosidozli.videoexample.data.Result;
import com.yosidozli.videoexample.data.model.User;

public class LoginViewModel extends ViewModel implements Observer<Result<User>> {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private LiveData<Result<User>> resultLiveData;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        if (resultLiveData != null){
            resultLiveData.removeObserver(this);
        }
        resultLiveData = loginRepository.login(username, password);
        resultLiveData.observeForever(this);

    }


    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(resultLiveData != null)
            resultLiveData.removeObserver(this);
    }

    @Override
    public void onChanged(Result<User> result) {
        if (result instanceof Result.Success) {
            User data = ((Result.Success<User>) result).getData();
            loginResult.postValue(new LoginResult(new LoggedInUserView(data.userId)));
            resultLiveData.removeObserver(this);
        } else {
            loginResult.postValue(new LoginResult(R.string.login_failed));
        }
    }
}