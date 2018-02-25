package com.example.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  public static final String LOGIN_ERROR = "Wrong username or password";

  @GetMapping("/login")
  public String login(Model model){
      return "login";
  }

  @GetMapping("/login-error")
    public String loginError(Model model){
      model.addAttribute("message", LOGIN_ERROR);
      return "login";
  }
}
