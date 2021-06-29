package com.yosidozli.videoexample.data;


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

    public Result<User> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            User user = appDao.getUser(username,password);
            if(user == null){
                user =
                        new User(
                                username,
                                password);
                final  User writeUser = user;
                executor.execute(() -> {
                    appDao.insertUser(writeUser);
                });
            }

            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}