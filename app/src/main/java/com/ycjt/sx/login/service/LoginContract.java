package com.ycjt.sx.login.service;

import com.ycjt.sx.login.bean.UserBean;

import java.util.ArrayList;

public class LoginContract {

    public interface LoginView {
        UserBean getUserBean();

    }

    public interface LoginPresenter {
        void login(UserBean user);
    }
}