package com.example.demo.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterRepository repo;

    // Menampilkan halaman registrasi
    @GetMapping
    public String register() {
        return "/Pelanggan/Register";
    }

    // Menangani form registrasi pengguna baru
    @PostMapping
    public String newUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("nama") String nama,
            Model model) {
        // Validasi sederhana (opsional)
        if (username.isEmpty() || password.isEmpty() || nama.isEmpty()) {
            model.addAttribute("error", "All fields are required!");
            return "/Pelanggan/Register";
        }

        // Generate noRekamMedis secara acak
        String noRekamMedis = RekamMedisGenerator.generateNoRekamMedis();

        // Buat user baru dengan noRekamMedis
        User user = new User(username, password, nama, "member", noRekamMedis);

        // Simpan data ke database
        repo.tambahUser(user);

        // Redirect ke halaman login setelah sukses
        return "redirect:/login";
    }
}
