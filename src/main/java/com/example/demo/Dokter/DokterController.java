package com.example.demo.Dokter;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

// import com.fasterxml.jackson.annotation.JsonCreator.Mode;

// import com.lighthouse.project.Other.PenggunaRepo;

// import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Transaksi.TransaksiRepo;

import java.util.Map;

@Controller
public class DokterController {

    @Autowired
    TransaksiRepo transaksiRepo;

    @Autowired
    DokterRepo repo;

    @GetMapping("/Register-Dokter")
    public String Register (Model model) {
        return "Dokter/Dokter-Register";
    }

    @GetMapping("/Login-Dokter")
    public String Login (Model model) {
        return "Dokter/Dokter-Login";
    }
    
    @PostMapping("/Login-Dokter-Data")
    public String log (String username, String password, HttpSession httpSession,Model model) {
        boolean isSuccess = repo.login(username, password);
        
        
        if(isSuccess){
            // String userType = repo.getUserType(username);
            // String nik = repo.getUserNik(username); // Fetch nik
            
            // userType = userType.equals("Agen") ? "atyp" : "ptyp" ;

            // httpSession.setAttribute("tipeuser", userType);
            httpSession.setAttribute("username", username);
            //httpSession.setAttribute("nik", nik);

  

            return "Dokter/SesudahLoginDokter";
        }

        return "Dokter/Dokter-Login";
    }


    @PostMapping("/Register-Dokter-Data")
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
            
            return "Dokter/SesudahLoginDokter";
        }

        return "Dokter/Dokter-Register";
    }


    @PostMapping("/add-diagnosa")
    public ResponseEntity<Map<String, String>> addDiagnosa(@RequestBody Map<String, Object> data) {
    int idTransaksi = Integer.parseInt(data.get("idTransaksi").toString());
    String hasilDiagnosa = data.get("hasilDiagnosa").toString();

    // Call the service method to update hasilDiagnosa
    boolean isSuccessful = transaksiRepo.addDiagnosa(idTransaksi, hasilDiagnosa);

    // Return the appropriate response
    return isSuccessful
        ? ResponseEntity.ok(Map.of("message", "Hasil diagnosa updated successfully."))
        : ResponseEntity.status(500).body(Map.of("error", "Failed to update hasil diagnosa."));
}

@PostMapping("/add-obat")
public ResponseEntity<Map<String, String>> addObat(@RequestBody Map<String, Object> data) {
    int idTransaksi = Integer.parseInt(data.get("idTransaksi").toString());
    String hasilPreskripsi = data.get("hasilPreskripsi").toString();

    // Call the service method to update hasilPreskripsi
    boolean isSuccessful = transaksiRepo.addObat(idTransaksi, hasilPreskripsi);

    // Return the appropriate response
    return isSuccessful
        ? ResponseEntity.ok(Map.of("message", "Hasil preskripsi updated successfully."))
        : ResponseEntity.status(500).body(Map.of("error", "Failed to update hasil preskripsi."));
}





    
}

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

//     @GetMapping("/setelahLoginDokter")
//     public String setelahLoginDokter() {
//         return "setelahLoginDokter"; // View for dokter dashboard
//     }

//     @GetMapping("/DiagnosisDokter")
//     public String diagnosisDokter() {
//         return "DiagnosisDokter"; // View for dokter's diagnosis page
//     }

//     @GetMapping("/ResepDokter")
//     public String resepDokter() {
//         return "ResepDokter"; // View for dokter's prescription page
//     }
// }

