package com.example.demo.Pelanggan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.JadwalDokter.JadwalDokterModel;
import com.example.demo.JadwalDokter.JadwalDokterRepo;

import jakarta.servlet.http.HttpSession;

import java.util.List;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PelangganController {

    @GetMapping("/sebelum-login-user")
    public String showSebelumLoginUser() {
        return "Pelanggan/SebelumLoginUser";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "Pelanggan/Login";
    }

    @GetMapping("/home")
    public String setelahLoginUser() {
        return "Pelanggan/SetelahLoginUser";
    }

    @GetMapping("/layanan")
    public String layanan() {
        return "Pelanggan/Layanan";
    }

    @GetMapping("/registerPelanggan")
    public String registerPage() {
        return "Pelanggan/Register";
    }

    @GetMapping("/faq")
    public String faqPage() {
        return "Pelanggan/FAQ";
    }

    @GetMapping("/informasi")
    public String informasiPage() {
        return "Pelanggan/Informasi";
    }

    @GetMapping("/jadwal-dokter")
    public String jadwalDokterPage() {
        return "Pelanggan/JadwalDokter";
    }

    @GetMapping("/pembayaran-berhasil")
    public String pembayaranBerhasilPage() {
        return "Pelanggan/PembayaranBerhasil";
    }

    @GetMapping("/pembayaran-riwayat")
    public String pembayaranRiwayatPage() {
        return "Pelanggan/PembayaranRiwayat";
    }

    @GetMapping("/pendaftaran-pasien")
    public String showPendaftaranPasien(Model model) {
        model.addAttribute("submitText", "SUBMIT");
        model.addAttribute("poliklinikName", "POLIKLINIK X");
        model.addAttribute("homeText", "Beranda");
        model.addAttribute("serviceText", "Layanan");
        model.addAttribute("infoText", "Informasi");
        model.addAttribute("searchText", "Search");
        model.addAttribute("aboutUsText", "Pendaftaran Pasien");
        model.addAttribute("breadcrumbText", "Beranda / Daftar Pasien");
        model.addAttribute("followUsText", "Follow us :");
        return "pendaftaran-pasien";
    }
}
