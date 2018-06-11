package com.example.demo.controller;

import com.example.demo.entity.user.User;
import com.example.demo.model.binding.UserBindingModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());

        return "/login";
    }

    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register", "userRegister", new UserBindingModel());
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegister") @Valid UserBindingModel userBindingModel,
                                 BindingResult result, WebRequest request, Errors errors) {
        System.out.println("I was here");
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(userBindingModel, result);
        }

        if (registered == null) {
            result.rejectValue("emailAddress", "message.regError");
        }

        if (result.hasErrors()) {
            return new ModelAndView("redirect:/index", "userRegister", userBindingModel);
        }
        else {
            return new ModelAndView("redirect:/index", "userRegister", userBindingModel);
        }
    }

    private User createUserAccount(UserBindingModel userBindingModel, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(userBindingModel);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return registered;
    }
}
