package com.example.controller.web;

import com.example.model.User;
import com.example.repository.service.UserService;
import com.example.utils.TimestampUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    public static final String SUCCESSFUL_REGISTRATION = "You have been registered successfuly";

    private UserService userService;
    private TimestampUtility timestampUtility;

    @Autowired
    public void setTimestampUtility(TimestampUtility timestampUtility) {
        this.timestampUtility = timestampUtility;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(Model model){
        User user = new User();
        model.addAttribute(user);
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes attributes){
        if(!result.hasErrors()){
            user.setDate(timestampUtility.getTimestamp());
            userService.addWithDefaultRole(user);
            attributes.addFlashAttribute("message", SUCCESSFUL_REGISTRATION);
            return "redirect:/";
        }
        return "register";
    }

}
