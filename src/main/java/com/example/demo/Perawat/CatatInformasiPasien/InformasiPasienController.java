package com.example.demo.Perawat.CatatInformasiPasien;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pasien")
public class InformasiPasienController {

    @GetMapping("/form")
    public String tampilkanForm() {
        return "Perawat/CatatInformasiPasien"; // Nama file HTML tanpa ekstensi
    }
}

