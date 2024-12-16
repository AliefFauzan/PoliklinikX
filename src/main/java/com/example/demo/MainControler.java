package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControler {
 
    @GetMapping("/")
    public String index (Model model) {
        return "/Pelanggan/SebelumLoginUSer";
    }

    // @GetMapping("/login")
    // public String Login (Model model) {
    //     return "/Login/Login";
    // }
    
    
    
}
