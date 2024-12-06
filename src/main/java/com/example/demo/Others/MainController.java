package com.example.demo.Others;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
//import com.lighthouse.project.Other.PenggunaRepo;

// import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @GetMapping("/")
    public String index (Model model) {
        return "SebelumLoginUSer";
    }

    @GetMapping("/register")
    public String Register (Model model) {
        return "Register";
    }

    @GetMapping("/Login")
    public String Login (Model model) {
        return "Login";
    }

    

}