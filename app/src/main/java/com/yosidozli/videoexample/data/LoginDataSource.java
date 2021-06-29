package com.yosidozli.videoexample.data;


import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.yosidozli.videoexample.data.model.User;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private AppDao appDao;

    public LoginDataSource(AppDao appDao) {
        this.appDao = appDao;
    }

    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<Result<User>> login(String username, String password) {

        return Transformations.map(appDao.getUser(username, password), user -> {
            try {
                if (user == null) {
                    User writeUser = new User(
                            username,
                            password);
                    executor.execute(() -> {
                        appDao.insertUser(writeUser);
                    });
                    return new Result.Success<>(writeUser);
                }
                return new Result.Success<>(user);

            } catch (Exception e) {
                return new Result.Error(new IOException("Error logging in", e));
            }
        });
    }
        public void logout() {
        // TODO: revoke authentication
    }
}