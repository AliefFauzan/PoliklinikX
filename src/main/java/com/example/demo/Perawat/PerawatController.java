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
import org.springframework.web.bind.annotation.RequestMapping;

// import com.fasterxml.jackson.annotation.JsonCreator.Mode;

// import com.lighthouse.project.Other.PenggunaRepo;

// import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/perawat")
@Controller
public class PerawatController {

    @Autowired
    PerawatRepo repo;

    @GetMapping("/Register")
    public String Register (Model model) {
        return "Perawat/Perawat-Register";
    }

    @GetMapping("/Login")
    public String Login (Model model) {
        return "Perawat/Perawat-Login";
    }
  

    @GetMapping("/sebelumLoginPerawat")
    public String sebelumLoginPerawat() {
        // Menampilkan halaman sebelum login
        return "Perawat/SebelumLoginPerawat";
    }

    @GetMapping("/sesudahLoginPerawat")
    public String sesudahLoginPerawat() {
        // Menampilkan halaman setelah login
        return "Perawat/SesudahLoginPerawat";
    }

    @GetMapping("/catatInformasiPasien")
    public String catatInformasiPasien() {
        // Menampilkan halaman catat informasi pasien
        return "Perawat/CatatInformasiPasien";
    }

    @PostMapping("/catatInformasiPasien")
    public String simpanInformasiPasien(
        @RequestParam("nomorRekamMedis") String nomorRekamMedis,
        @RequestParam("keluhan") String keluhan,
        @RequestParam("golonganDarah") String golonganDarah,
        @RequestParam("beratBadan") Double beratBadan,
        @RequestParam("namaDokter") String namaDokter,
        @RequestParam("jadwalDokter") String jadwalDokter,
        @RequestParam("suhu") Double suhu,
        @RequestParam("tekananDarah") String tekananDarah,
        @RequestParam("tinggiBadan") Double tinggiBadan,
        Model model) {
        // Simpan data pasien ke database (contoh: log data untuk saat ini)
        System.out.println("Informasi pasien disimpan: " + nomorRekamMedis);
        model.addAttribute("message", "Informasi pasien berhasil disimpan.");
        return "Perawat/CatatInformasiPasien";
    }

    @GetMapping("/catatRekamMedis")
    public String catatRekamMedis() {
        // Menampilkan halaman catat rekam medis
        return "Perawat/CatatRekamMedis";
    }

    @PostMapping("/catatRekamMedis")
    public String simpanRekamMedis(
        @RequestParam("nomorRekamMedis") String nomorRekamMedis,
        @RequestParam("riwayatPenyakit") String riwayatPenyakit,
        @RequestParam("hasilLab") String hasilLab,
        @RequestParam("alergi") String alergi,
        Model model) {
        // Simpan data rekam medis ke database (contoh: log data untuk saat ini)
        System.out.println("Rekam medis disimpan: " + nomorRekamMedis);
        model.addAttribute("message", "Rekam medis berhasil disimpan.");
        return "Perawat/CatatRekamMedis";
    }
}
