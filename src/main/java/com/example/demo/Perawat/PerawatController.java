package com.example.demo.Perawat;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

// import com.fasterxml.jackson.annotation.JsonCreator.Mode;

// import com.lighthouse.project.Other.PenggunaRepo;

// import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import jakarta.servlet.http.HttpSession;

@Controller
public class PerawatController {

    @Autowired
    PerawatRepo repo;

    @GetMapping("/Register-Perawat")
    public String Register (Model model) {
        return "Perawat/Perawat-Register";
    }

    @GetMapping("/Login-Perawat")
    public String Login (Model model) {
        return "Perawat/Perawat-Login";
    }
    
    @PostMapping("/login-Perawat-Data")
    public String log (String username, String password, HttpSession httpSession,Model model) {
        boolean isSuccess = repo.login(username, password);
        
        
        if(isSuccess){
            // String userType = repo.getUserType(username);
            // String nik = repo.getUserNik(username); // Fetch nik
            
            // userType = userType.equals("Agen") ? "atyp" : "ptyp" ;

            // httpSession.setAttribute("tipeuser", userType);
            httpSession.setAttribute("username", username);
            //httpSession.setAttribute("nik", nik);

  

            return "Perawat/SesudahLoginPerawat";
        }

        return "Perawat/Perawat-Login";
    }


    @PostMapping("/Register-Perawat-Data")
    public String reg(
    String idPegawai, 
    String username, 
    String password,
    String nama, 
    String confirmPassword,
    HttpSession httpSession, Model model){
        
        
        boolean isSuccess = repo.register(username, nama, password,  confirmPassword);
        
        if(isSuccess){
        
            httpSession.setAttribute("username", username);
            // httpSession.setAttribute("tipeuser", userType);
            // httpSession.setAttribute("username", username);
            
            return "Perawat/SesudahLoginPerawat";
        }

        return "Perawat/Admin-Register";
    }

    
}
