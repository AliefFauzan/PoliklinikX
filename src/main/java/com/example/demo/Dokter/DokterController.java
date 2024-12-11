package com.example.demo.Dokter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DokterController {

    @GetMapping("/login")
    public String dokterLoginForm() {
        return "dokterLogin"; // View for the dokter login page
    }

    @PostMapping("/login")
    public String dokterLoginSubmit(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     Model model) {
        // Example credentials check (replace with actual logic)
        if ("dokterUser".equals(username) && "dokterPass".equals(password)) {
            return "redirect:/setelahLoginDokter";
        } else {
            model.addAttribute("error", "Username atau password salah");
            return "dokterLogin"; // Redirect back to login page with error message
        }
    }

    @GetMapping("/setelahLoginDokter")
    public String setelahLoginDokter() {
        return "setelahLoginDokter"; // View for dokter dashboard
    }

    @GetMapping("/DiagnosisDokter")
    public String diagnosisDokter() {
        return "DiagnosisDokter"; // View for dokter's diagnosis page
    }

    @GetMapping("/ResepDokter")
    public String resepDokter() {
        return "ResepDokter"; // View for dokter's prescription page
    }
}
