package com.example.demo.service;

import com.example.demo.entity.user.User;
import com.example.demo.model.dto.UserBindingModel;

public interface UserService {
    User registerNewUserAccount(UserBindingModel userBindingModel);
    boolean emailExist(String email);
}
