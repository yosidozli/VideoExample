package com.yosidozli.videoexample.ui.login;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.yosidozli.videoexample.data.AppDatabase;
import com.yosidozli.videoexample.data.LoginDataSource;
import com.yosidozli.videoexample.data.LoginRepository;

import org.jetbrains.annotations.NotNull;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public LoginViewModelFactory(@NonNull @NotNull Application application) {
        super(application);
        this.context = application;
    }

    private Context context;

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(AppDatabase.getInstance(context).appDao())));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}