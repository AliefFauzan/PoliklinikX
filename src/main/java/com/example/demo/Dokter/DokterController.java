package com.example.demo.Dokter;


import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.jdbc.core.JdbcTemplate;
// import jakarta.activation.DataSource;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DokterController {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DokterRepo repo;


//     @GetMapping("/Register-Dokter")
//     public String Register (Model model) {
//         return "Dokter/Dokter-Register";
//     }

//     @GetMapping("/Login-Dokter")
//     public String Login (Model model) {
//         return "Dokter/Dokter-Login";
//     }
    
//     @PostMapping("/login-Dokter-Data")
//     public String log (String username, String password, HttpSession httpSession,Model model) {
//         boolean isSuccess = repo.login(username, password);
        
        
//         if(isSuccess){
//             // String userType = repo.getUserType(username);
//             // String nik = repo.getUserNik(username); // Fetch nik
            
//             // userType = userType.equals("Agen") ? "atyp" : "ptyp" ;

//             // httpSession.setAttribute("tipeuser", userType);
//             httpSession.setAttribute("username", username);
//             //httpSession.setAttribute("nik", nik);

  

//             return "Dokter/SesudahLoginDokter";
//         }

//         return "Dokter/Dokter-Login";
//     }


//     @PostMapping("/Register-Dokter-Data")
//     public String reg(
//     String idPegawai, 
//     String username, 
//     String password,
//     String nama, 
//     String confirmPassword,
//     HttpSession httpSession, Model model){
        
        
//         boolean isSuccess = repo.register(username, nama, password,  confirmPassword);
        
//         if(isSuccess){
        
//             httpSession.setAttribute("username", username);
//             // httpSession.setAttribute("tipeuser", userType);
//             // httpSession.setAttribute("username", username);
            
//             return "Dokter/SesudahLoginDokter";
//         }

//         return "Dokter/Dokter-Register";
//     }

    
// }

//     @GetMapping("/login")
//     public String dokterLoginForm() {
//         return "dokterLogin"; // View for the dokter login page
//     }

//     @PostMapping("/login")
//     public String dokterLoginSubmit(@RequestParam("username") String username,
//                                      @RequestParam("password") String password,
//                                      Model model) {
//         // Example credentials check (replace with actual logic)
//         if ("dokterUser".equals(username) && "dokterPass".equals(password)) {
//             return "redirect:/setelahLoginDokter";
//         } else {
//             model.addAttribute("error", "Username atau password salah");
//             return "dokterLogin"; // Redirect back to login page with error message
//         }
//     }

    @GetMapping("/setelahLoginDokter")
    public String setelahLoginDokter() {
        return "/Dokter/SesudahLoginDokter"; // View for dokter dashboard
    }

    @GetMapping("/DiagnosisDokter")
    public String diagnosisDokter() {
        return "/Dokter/DiagnosaDokter"; // View for dokter's diagnosis page
    }

    @GetMapping("/ResepDokter")
    public String resepDokter() {
        return "ResepDokter"; // View for dokter's prescription page
    }

    @PostMapping("/simpanResep")
    public String simpanResep(
            @RequestParam("namaObat") String namaObat,
            @RequestParam("dosisObat") String dosisObat,
            @RequestParam("noRekamMedis") String noRekamMedis,
            Model model) {
        try {
            String query = "UPDATE Transaksi SET hasilPreskripsi = ? WHERE noRekamMedis = ?";
            int updatedRows = jdbcTemplate.update(query, namaObat + " - " + dosisObat, noRekamMedis);

            if (updatedRows > 0) {
                model.addAttribute("message", "Resep berhasil disimpan untuk pasien dengan No. Rekam Medis " + noRekamMedis);
            } else {
                model.addAttribute("error", "Pasien dengan No. Rekam Medis tersebut tidak ditemukan.");
            }
            return "ResepDokter";
        } catch (Exception e) {
            model.addAttribute("error", "Terjadi kesalahan saat menyimpan resep: " + e.getMessage());
            return "ResepDokter";
        }
    }

    @PostMapping("/simpanDiagnosa")
    public String simpanDiagnosa(
            @RequestParam("noRekamMedis") String noRekamMedis,
            @RequestParam("hasilDiagnosa") String hasilDiagnosa,
            Model model) {
        try {
            // SQL query to update the diagnosis
            String query = "UPDATE Transaksi SET hasilDiagnosa = ? WHERE noRekamMedis = ?";
            int updatedRows = jdbcTemplate.update(query, hasilDiagnosa, noRekamMedis);

            if (updatedRows > 0) {
                model.addAttribute("message", "Diagnosa berhasil disimpan untuk pasien dengan No. Rekam Medis " + noRekamMedis);
            } else {
                model.addAttribute("error", "Pasien dengan No. Rekam Medis tersebut tidak ditemukan.");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Terjadi kesalahan saat menyimpan diagnosa: " + e.getMessage());
        }
        return "DiagnosisDokter"; // Return to the diagnosis page
    }
}
