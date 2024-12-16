package com.example.demo.Perawat;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Pasien.PasienRepo;
import com.example.demo.Transaksi.TransaksiModel;
import com.example.demo.Transaksi.TransaksiRepo;

@Controller
public class PerawatController {

    @Autowired
    PerawatRepo repo;

    @Autowired
    TransaksiRepo TransaksiRepo;

    @Autowired
    PasienRepo PasienRepo;

    @GetMapping("/Register-Perawat")
    public String Register (Model model) {
        return "Perawat/Perawat-Register";
    }

    @GetMapping("/Login-Perawat")
    public String Login (Model model) {
        return "Perawat/Perawat-Login";
    }
    
    @PostMapping("/Login-Perawat-Data")
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
        return "Perawat/SesudahLoginPerawat";
        // return "Perawat/Perawat-Login";
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

    @GetMapping("/CatatInformasiPasien")
    public String catatInformasiPasien(Model model) {
        List<TransaksiModel> transaksis = TransaksiRepo.findAllTransaksi();
        model.addAttribute("transaksis", transaksis);
        // Menampilkan halaman catat informasi pasien
        return "Perawat/CatatInformasiPasien";
    }

    //nanti ditambah ngelisten ke repo
    // @PostMapping("/catatInformasiPasienInput")
    // public String simpanInformasiPasien(
    //     @RequestParam("nomorRekamMedis") String nomorRekamMedis,
    //     @RequestParam("keluhan") String keluhan,
    //     @RequestParam("golonganDarah") String golonganDarah,
    //     @RequestParam("beratBadan") Double beratBadan,
    //     @RequestParam("namaDokter") String namaDokter,
    //     @RequestParam("jadwalDokter") String jadwalDokter,
    //     @RequestParam("suhu") Double suhu,
    //     @RequestParam("tekananDarah") String tekananDarah,
    //     @RequestParam("tinggiBadan") Double tinggiBadan,
    //     Model model) {
    //     // Simpan data pasien ke database (contoh: log data untuk saat ini)
    //     System.out.println("Informasi pasien disimpan: " + nomorRekamMedis);
    //     model.addAttribute("message", "Informasi pasien berhasil disimpan.");
    //     return "Perawat/CatatInformasiPasien";
    // }
    @PostMapping("/CatatInformasiPasienInput")
    public String simpanInformasiPasien(@RequestParam int idTransaksi, @RequestParam String keluhan) {
    TransaksiRepo.updateKeluhan(idTransaksi, keluhan);
     return "Perawat/CatatInformasiPasien"; // Redirect to the updated list view
    }


    @GetMapping("/CatatRekamMedis")
    public String catatRekamMedis() {
        // Menampilkan halaman catat rekam medis
        return "Perawat/CatatRekamMedis";
    }

    // @PostMapping("/catatRekamMedisInput")
    // public String simpanRekamMedis(
    //     @RequestParam("nomorRekamMedis") String nomorRekamMedis,
    //     @RequestParam("riwayatPenyakit") String riwayatPenyakit,
    //     @RequestParam("hasilLab") String hasilLab,
    //     @RequestParam("alergi") String alergi,
    //     Model model) {
    //     // Simpan data rekam medis ke database (contoh: log data untuk saat ini)
    //     System.out.println("Rekam medis disimpan: " + nomorRekamMedis);
    //     model.addAttribute("message", "Rekam medis berhasil disimpan.");
    //     return "Perawat/CatatRekamMedis";
    // }

    @PostMapping("/CatatRekamMedisInput")
    public String addDataRekamMedis(@RequestParam int noRekamMedis, @RequestParam String dataRekamMedis) {
        PasienRepo.updateDataRekamMedis(noRekamMedis, dataRekamMedis);
        return "redirect:/pasienList"; // Redirect to the updated list view of Pasien
    }
}
