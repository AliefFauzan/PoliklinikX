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


@Controller
public class PelangganController {


    // Map to SebelumLoginUser page
    @GetMapping("/sebelum-login-user")
    public String showSebelumLoginUser() {
        return "Pelanggan/SebelumLoginUser"; // Pastikan path ini sesuai dengan struktur template Anda
    }

    // Map to the Login page
    @GetMapping("/login")
    public String loginPage() {
        return "Pelanggan/Login"; // Nama template Thymeleaf tanpa ".html"

    }

    @GetMapping("/home")
    public String setelahLoginUser() {
        return "Pelanggan/SetelahLoginUser"; // Path template
    }

    @GetMapping("/layanan")
    public String layanan() {
        return "Pelanggan/Layanan";
    }

    // Map to the Register page
    @GetMapping("/registerPelanggan")
    public String registerPage() {
        return "Pelanggan/Register";
    }

    // Map to the FAQ page
    @GetMapping("/faq")
    public String faqPage() {
        return "Pelanggan/FAQ";
    }

    // Map to the Informasi page
    @GetMapping("/informasi")
    public String informasiPage() {
        return "Pelanggan/Informasi";
    }

    // Map to Jadwal Dokter page
    @GetMapping("/jadwal-dokter")
    public String jadwalDokterPage() {
        return "Pelanggan/JadwalDokter";
    }

    // Map to Janji Temu page
    @GetMapping("/janji-temu")
    public String janjiTemuPage() {
        return "Pelanggan/JanjiTemu";
    }

    @PostMapping("/Pilih-Dokter")
    public String pilihDokter (
        @RequestParam(required = false) String spesialisasi,
        @RequestParam(required = false) String nama,
        @SessionAttribute(value = "spesialisasi", required = false) String sessionSpesialisasi,
        @SessionAttribute(value = "nama", required = false) String sessionNama,
        Model model
    ) {
        spesialisasi = (spesialisasi != null) ? spesialisasi : sessionSpesialisasi;
        nama = (nama != null) ? nama : sessionNama;

        // Save to session attributes
        model.addAttribute("spesialisasi", spesialisasi);
        model.addAttribute("nama", nama);
        List<JadwalDokterModel> jadwalDokters;
        

        if (spesialisasi != null && nama != null) {
            jadwalDokters = jadwalDokterRepo.findJadwalDokterByNamaAndSpesialisasi(nama, spesialisasi);
        } else if (spesialisasi != null) {
            jadwalDokters = jadwalDokterRepo.findJadwalDokterBySpesialisasi(spesialisasi);
        } else {
            jadwalDokters = jadwalDokterRepo.findAllJadwalDokter();
        }

        model.addAttribute("jadwalDokters", jadwalDokters);
        return "jadwalDokterView"; // Name of the HTML/JSP view to render
    }


    
   

    // Map to Pembayaran page
    @GetMapping("/pembayaran")
    public String pembayaranPage() {
        return "Pelanggan/Pembayaran";
    }

    @PostMapping("/Pembayaran-Form")
    //TODO: nyambungin ini ke form
    // public String accPembayaran(
    //     @RequestParam(name="paymentOption") String statuspembayaran,
    //     HttpSession httpSession, Model model){
    //         TowerUnitModel unit = (TowerUnitModel) httpSession.getAttribute("dataUnit");
    //         PenggunaModel pelanggan = (PenggunaModel) httpSession.getAttribute("pelanggan");

    //         String checkIn = String.valueOf(httpSession.getAttribute("dataCheckIn"));
    //         String checkOut = String.valueOf(httpSession.getAttribute("dataCheckOut"));

    //         httpSession.setAttribute("statuspembayaran", statuspembayaran);

    //         System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    //         boolean isSuccessful = trRepo.addTransaction(checkIn, checkOut, pelanggan, unit, statuspembayaran);
            
    //         if(isSuccessful){
    //             return "redirect:/ptyp/riwayat";
    //         }else{
    //             return "pembayaran";
    //         }
            
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
