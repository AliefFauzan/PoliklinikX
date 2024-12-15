package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControler {
 
    @GetMapping("/")
    public String index (Model model) {
        return "Pelanggan/SebelumLoginUser";
    }

    @GetMapping("/register")
    public String Register (Model model) {
        return "RegisterUmum";
    }

    @GetMapping("/Login")
    public String Login (Model model) {
        return "LoginUmum";
    }
    
    
    
}
