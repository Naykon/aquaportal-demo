package com.example.demo.service;

import com.example.demo.entity.user.User;
import com.example.demo.model.dto.UserBindingModel;
import com.example.demo.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public User registerNewUserAccount(UserBindingModel userBindingModel) throws IllegalArgumentException {
        if (emailExist(userBindingModel.getEmailAddress())) {
            throw new IllegalArgumentException(
                    "There is an account with that email adress: "
                            +  userBindingModel.getEmailAddress());
        }

        User user = new User();
        user.setFirstName(userBindingModel.getFirstName());
        user.setLastName(userBindingModel.getLastName());
        user.setPassword(userBindingModel.getPassword());
        user.setEmailAddress(userBindingModel.getEmailAddress());
        user.setUsername(userBindingModel.getUsername());
        user.setEnabled(true);
        user.setRole(roleService.findUser());

        return userRepository.save(user);
    }

    @Override
    public boolean emailExist(String email) {
        User user = userRepository.findByEmailAddress(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
