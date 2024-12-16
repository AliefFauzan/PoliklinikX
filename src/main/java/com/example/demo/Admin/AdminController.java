package com.example.demo.Admin;
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
import org.springframework.web.bind.annotation.RequestParam;

// import com.example.demo.Dokter.Dokter;
import com.example.demo.Dokter.DokterModel;
import com.example.demo.Dokter.DokterRepo;
import com.example.demo.JadwalDokter.JadwalDokterModel;
import com.example.demo.JadwalDokter.JadwalDokterRepo;
import com.example.demo.Transaksi.TransaksiModel;
import com.example.demo.Transaksi.TransaksiRepo;

// import com.fasterxml.jackson.annotation.JsonCreator.Mode;

// import com.lighthouse.project.Other.PenggunaRepo;

// import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdminRepo repo;

    @Autowired
    TransaksiRepo TransaksiRepo;

    @Autowired
    private JadwalDokterRepo jadwalDokterRepo;

    @Autowired
    private DokterRepo DokterRepo;

    @GetMapping("/Register-Admin")
    public String Register (Model model) {
        return "Admin/Admin-Register";
    }

    @GetMapping("/Login-Admin")
    public String Login (Model model) {
        return "Admin/Admin-Login";
    }
    
    @PostMapping("/Login-Admin-Data")
    public String log (String username, String password, HttpSession httpSession,Model model) {
        boolean isSuccess = repo.login(username, password);
        
        
        if(isSuccess){
            // String userType = repo.getUserType(username);
            // String nik = repo.getUserNik(username); // Fetch nik
            
            // userType = userType.equals("Agen") ? "atyp" : "ptyp" ;

            // httpSession.setAttribute("tipeuser", userType);
            httpSession.setAttribute("username", username);
            //httpSession.setAttribute("nik", nik);

  

            return "Admin/SesudahLoginAdmin";
        }
        return "Admin/SesudahLoginAdmin";
        // return "Admin/Admin-Login";
    }


    @PostMapping("/Register-Admin-Data")
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
            
            return "Admin/SesudahLoginAdmin";
        }

        return "Admin/Admin-Register";
    }
    

    @GetMapping("/JadwalDokter")
    public String jadwalDokter (Model model) {
        List<JadwalDokterModel> jadwalDokters = jadwalDokterRepo.findAllJadwalDokter();
        List<DokterModel> dokters = DokterRepo.findAllDokter();
        model.addAttribute("jadwalDokters", jadwalDokters);
        model.addAttribute("dokters", dokters);
        return "Admin/KelolaJadwalDokter";
    }

    @PostMapping("/Add-Jadwal-To-Form")
    public String addJadwalDokter(@RequestParam int idDokter, @RequestParam String hari, 
                                   @RequestParam int jamMulai, @RequestParam int jamSelesai) {
        jadwalDokterRepo.addJadwalDokter(idDokter, hari, jamMulai, jamSelesai);
        return "Admin/KelolaJadwalDokter"; // Redirect to the updated list view
    }

    @PostMapping("/edit-jadwal-dokter")
    public String changeJadwalDokter(@RequestParam int idJadwal, @RequestParam String hari, 
                                 @RequestParam int jamMulai, @RequestParam int jamSelesai) {
    jadwalDokterRepo.updateJadwalDokter(idJadwal, hari, jamMulai, jamSelesai);
    return "Admin/KelolaJadwalDokter"; // Redirect to the updated list view
}
    @GetMapping("/Catat-Bayar")
    public String catatBayar (Model model) {
        List<TransaksiModel> transaksis = TransaksiRepo.findAllTransaksi();
        model.addAttribute("transaksis", transaksis);
        return "Admin/CatatBayar";
    }

    @PostMapping("/Add-Catat-Bayar")
    public String addHTarif(@RequestParam int idTransaksi, @RequestParam int tarif) {
        TransaksiRepo.updateTarif(idTransaksi, tarif);
        return "Admin/CatatBayar"; // Redirect to the updated list view
    }


//daftar ulang harusnya
    @GetMapping("/dataRekamMedis")
    public String dataRekamMedis() { 
        return "Admin/DaftarPasien";
    }
    

}
