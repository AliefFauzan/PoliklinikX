package com.example.demo.Pasien;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.JadwalDokter.JadwalDokterModel;
import com.example.demo.JadwalDokter.JadwalDokterRepo;
import com.example.demo.Transaksi.TransaksiModel;
import com.example.demo.Transaksi.TransaksiRepo;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
//import com.lighthouse.project.Other.PenggunaRepo;

// import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import jakarta.servlet.http.HttpSession;

@Controller
public class PasienController {

    @Autowired
    private PasienRepo repo;

    @Autowired
    private TransaksiRepo TransaksiRepo;

    @Autowired
    private JadwalDokterRepo JadwalDokterRepo;

    @GetMapping("/Register-Pasien")
    public String Register (Model model) {
        return "Pelanggan/Register";
    }

    @GetMapping("/Login-Pasien")
    public String Login (Model model) {
        return "Pelanggan/Login";
    }

    @PostMapping("/Register-Pasien-Data")
    public String reg(
    String noRekamMedis, 
    String dataRekamMedis, 
    String username, 
    String password,
    String nama, 
    String tanggalLahir,
    String alamat,
    String confirmPassword,
    HttpSession httpSession, Model model){
        
        
        boolean isSuccess = repo.register(username, nama, password,  confirmPassword,  tanggalLahir,  alamat);
        
        if(isSuccess){
        
            httpSession.setAttribute("username", username);
            // httpSession.setAttribute("tipeuser", userType);
            // httpSession.setAttribute("username", username);
            
            return "Pasien/SetelahLoginUser";
        }

        return "Pasien/register";
    }


    @PostMapping("/Login-Pasien-Data")
    public String log(String username, String password, HttpSession httpSession,Model model){
        boolean isSuccess = repo.login(username, password);
        
        
        if(isSuccess){
            // String userType = repo.getUserType(username);
            // String nik = repo.getUserNik(username); // Fetch nik
            
            // userType = userType.equals("Agen") ? "atyp" : "ptyp" ;

            // httpSession.setAttribute("tipeuser", userType);
            httpSession.setAttribute("username", username);
            //httpSession.setAttribute("nik", nik);

  

            return "Pasien/SetelahLoginUser";
        }


        return "Pasien/login";
    }

    @GetMapping("/Signout")
    public String signout(HttpSession session) {
    // Invalidate the session to clear user data
        session.invalidate();
    

        return "redirect:/";
    }

     // Map to Janji Temu page
     @GetMapping("/janji-temu")
     public String janjiTemuPage(Model model) {
        List<JadwalDokterModel> dokters = JadwalDokterRepo.findAllJadwalDokter();
        model.addAttribute("dokters", dokters);
         return "Pelanggan/JanjiTemu";
     }

     @PostMapping("/Pilih-Dokter")
     public String addTransaction(@RequestParam int noRekamMedis, @RequestParam String namaDokter, 
                                 @RequestParam String hari, @RequestParam int jam, 
                                 @RequestParam int tarif) {
        TransaksiRepo.addTransaction(noRekamMedis, namaDokter, hari, jam, tarif);
        return "Pelanggan/JanjiTemu";
    }
        
         // Redirect to the updated list view
    
     

    // @PostMapping("/Pilih-Dokter")
    // public String pilihDokter (
    //     @RequestParam(required = false) String spesialisasi,
    //     @RequestParam(required = false) String nama,
    //     @SessionAttribute(value = "spesialisasi", required = false) String sessionSpesialisasi,
    //     @SessionAttribute(value = "nama", required = false) String sessionNama,
    //     Model model
    // ) {
    //     spesialisasi = (spesialisasi != null) ? spesialisasi : sessionSpesialisasi;
    //     nama = (nama != null) ? nama : sessionNama;

    //     // Save to session attributes
    //     model.addAttribute("spesialisasi", spesialisasi);
    //     model.addAttribute("nama", nama);
    //     List<JadwalDokterModel> jadwalDokters;
        

    //     if (spesialisasi != null && nama != null) {
    //         jadwalDokters = jadwalDokterRepo.findJadwalDokterByNamaAndSpesialisasi(nama, spesialisasi);
    //     } else if (spesialisasi != null) {
    //         jadwalDokters = jadwalDokterRepo.findJadwalDokterBySpesialisasi(spesialisasi);
    //     } else {
    //         jadwalDokters = jadwalDokterRepo.findAllJadwalDokter();
    //     }

    //     model.addAttribute("jadwalDokters", jadwalDokters);
    //     return "jadwalDokterView"; // Name of the HTML/JSP view to render
    // }

     // Map to Pembayaran page
    @GetMapping("/pembayaran")
    public String pembayaranPage(Model model) {
        
        return "Pelanggan/Pembayaran";
    }

    @PostMapping("/add-pembayaran")
    public String addMetodeBayar(@RequestParam int idTransaksi, @RequestParam String metodePembayaran) {
        TransaksiRepo.updateMetodePembayaran(idTransaksi, metodePembayaran);
        return "Pelanggan/Pembayaran"; // Redirect to the updated list view
    }








    

}