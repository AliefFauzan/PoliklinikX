package com.example.demo.Pelanggan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Pelanggan.JanjiTemu.JanjiTemu;

@Controller
public class PelangganController {

    // Map to SebelumLoginUser page
    @GetMapping("/sebelum-login-user")
    public String showSebelumLoginUser() {
        return "Pelanggan/SebelumLoginUser"; // Pastikan path ini sesuai dengan struktur template Anda
    }

    // Map to the Login page
    // @GetMapping("/login")
    // public String loginPage() {
    //     return "Pelanggan/Login"; // Nama template Thymeleaf tanpa ".html"

    // }

    @GetMapping("/home")
    public String setelahLoginUser() {
        return "Pelanggan/SetelahLoginUser"; // Path template
    }

    @GetMapping("/layanan")
    public String layanan() {
        return "Pelanggan/Layanan";
    }

    // // Map to the Register page
    // @GetMapping("/register")
    // public String registerPage() {
    //     return "PetugasFiturUmum/Register";
    // }

    // Map to the FAQ page
    @GetMapping("/faq")
    public String faqPage() {
        return "Pelanggan/FAQ";
    }

    // Map to the FAQ page
    @GetMapping("/SebelumFAQ")
    public String sebelumfaqPage() {
        return "Pelanggan/SebelumFAQ";
    }


    // Map to the Informasi page
    @GetMapping("/informasi")
    public String informasiPage() {
        return "Pelanggan/Informasi";
    }
@GetMapping("/SebelumInformasi")
    public String sebeluminformasiPage() {
        return "Pelanggan/SebelumInformasi";
    }

    // Map to Jadwal Dokter page
    @GetMapping("/jadwal-dokter")
    public String jadwalDokterPage() {
        return "Pelanggan/JadwalDokter";
    }

    // Map to Janji Temu page
    @GetMapping("/janji-temu")
    public String showJanjiTemuForm(Model model) {
        JanjiTemu janjiTemu = new JanjiTemu(); // Buat objek baru
        model.addAttribute("janjiTemu", janjiTemu);
        return "Pelanggan/JanjiTemu";  // Pastikan nama file template sesuai
    }

    // Map to Pembayaran page
    @GetMapping("/pembayaran")
    public String pembayaranPage() {
        return "Pelanggan/Pembayaran";
    }

    // Handle successful payment
    @GetMapping("/pembayaran-berhasil")
    public String pembayaranBerhasilPage() {
        return "Pelanggan/PembayaranBerhasil";
    }

    // Map to Pembayaran Riwayat page
    @GetMapping("/pembayaran-riwayat")
    public String pembayaranRiwayatPage() {
        return "Pelanggan/PembayaranRiwayat";
    }

    // Map to Pendaftaran Pasien Pelanggan page
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