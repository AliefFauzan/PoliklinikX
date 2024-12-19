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

import com.example.demo.Dokter.DokterModel;
import com.example.demo.Dokter.DokterRepo;
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

    @Autowired
    private PasienRepo PasienRepo;
    @Autowired
    private DokterRepo DokterRepo;

   

    @GetMapping("/Register-Pasien")
    public String Register (Model model) {
        return "Pelanggan/Pelanggan-Register";
    }

    @GetMapping("/Login-Pasien")
    public String Login (Model model) {
        return "Pelanggan/Pelanggan-Login";
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

  

            return "Pelanggan/SetelahLoginUser";
        }
        httpSession.setAttribute("username", username);

        // return "Pelanggan/Pelanggan-login";
        return "Pelanggan/SetelahLoginUser";
    }

    @GetMapping("/Signout")
    public String signout(HttpSession session) {
    // Invalidate the session to clear user data
        session.invalidate();
    

        return "redirect:/";
    }

     // Map to Janji Temu page
     @GetMapping("/janji-temu")
     public String janjiTemuPage(HttpSession session, Model model) {
        List<JadwalDokterModel> jadwalDokters = JadwalDokterRepo.findAllJadwalDokter();
        List<PasienModel> pasienList = PasienRepo.findAllPasien();
        String pasien = (String) session.getAttribute("username");
        if (pasien == null) {
            return "redirect:/login"; // Redirect to login page if no username in session
        }
        List<PasienModel> pasienByUsername = PasienRepo.findPasienByUsername(pasien);
        model.addAttribute("pasienByUsername", pasienByUsername);
        model.addAttribute("jadwalDokters", jadwalDokters);
         return "Pelanggan/JanjiTemu";
     }

     @PostMapping("/Pilih-Dokter")
     public String addTransaction(@RequestParam("nama") String nama, 
     @RequestParam("namaDokter") String namaDokter, 
     @RequestParam("jam") int jam, @RequestParam("hari") String hari,
     HttpSession session, 
     Model model) {
        List<PasienModel> pasienList = PasienRepo.findPasienByNama(nama);
        // List<JadwalDokterModel> jadwalDokterList = JadwalDokterRepo.findJadwalDokterByNama(namaDokter);

    // Check if the patient exists
    if (pasienList == null || pasienList.isEmpty()) {
        model.addAttribute("error", "Patient not found.");
        return "Pelanggan/ErrorPage"; // Redirect to an error page or handle appropriately
    }

    // Assuming you expect only one result, retrieve the first patient
    PasienModel pasien = pasienList.get(0);
    // DokterModel dokter = dokterList.get(0);

    // Get the medical record number
    int rekamMedis = pasien.getNoRekamMedis();
    

        TransaksiRepo.addTransaction (rekamMedis, namaDokter, hari, jam);
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
    public String pembayaranPage(Model model, HttpSession session) {
        String pasien = (String) session.getAttribute("username");
        List<TransaksiModel> transaksi = TransaksiRepo.findTransaksiByUsername(pasien);
        
        model.addAttribute("transaksi", transaksi);

        return "Pelanggan/Pembayaran";
    }

    @PostMapping("/add-pembayaran")
    public String addMetodeBayar(@RequestParam int idTransaksi, @RequestParam String metodePembayaran) {
        TransaksiRepo.updateMetodePembayaran(idTransaksi, metodePembayaran);
        return "Pelanggan/Pembayaran"; // Redirect to the updated list view
    }








    

}