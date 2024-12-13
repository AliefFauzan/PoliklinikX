package com.example.demo.Admin;
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
public class AdminController {

    @Autowired
    AdminRepo repo;

    @GetMapping("/Register-Admin")
    public String Register (Model model) {
        return "Admin/Admin-Register";
    }

    @GetMapping("/Login-Admin")
    public String Login (Model model) {
        return "Admin/Admin-Login";
    }
    
    @PostMapping("/:Login-Admin-Data")
    public String log (String username, String password, HttpSession httpSession,Model model) {
        boolean isSuccess = repo.login(username, password);
        
        
        if(isSuccess){
            // String userType = repo.getUserType(username);
            // String nik = repo.getUserNik(username); // Fetch nik
            
            // userType = userType.equals("Agen") ? "atyp" : "ptyp" ;

            // httpSession.setAttribute("tipeuser", userType);
            httpSession.setAttribute("username", username);
            //httpSession.setAttribute("nik", nik);

  

            return "Admin/SesudahLoginAdmin";
        }

        return "Admin/Admin-Login";
    }


    @PostMapping("/Register-Admin-Data")
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
            
            return "Admin/SesudahLoginAdmin";
        }

        return "Admin/Admin-Register";
    }

    
}
