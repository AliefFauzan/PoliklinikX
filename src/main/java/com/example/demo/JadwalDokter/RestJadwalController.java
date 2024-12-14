package com.example.demo.JadwalDokter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.lighthouse.project.KetersediaanUnit.KetersediaanRepo;
// import com.lighthouse.project.KetersediaanUnit.KetersediaanService;
// import com.lighthouse.project.KetersediaanUnit.TransaksiKetersediaanModel;

import jakarta.servlet.http.HttpSession;

@RestController
public class RestJadwalController {

    @Autowired
    private JadwalDokterRepo jadwalDokterRepo;
    //kayaknya id dokter apus aja deh
    @PostMapping("/Add-Jadwal-To-Form")
    public ResponseEntity<Map<String,String>> addJadwal(@RequestBody Map<String,Object> data){
        long idDokter = Long.parseLong((String) data.get("idDokter"));
        String nama = (String) data.get("nama");
        String spesialisasi = (String) data.get("spesialisasi");
        String hari = (String) data.get("hari");
        int jamMulai = Integer.parseInt((String) data.get("jamMulai"));
        int jamSelesai = Integer.parseInt((String) data.get("jamSelesai"));
        
        boolean isSuccessful = jadwalDokterRepo.addJadwalDokterData(new JadwalDokterModel(0, idDokter, nama, spesialisasi, hari, jamMulai, jamSelesai));
       
        
    

    
        return validate(isSuccessful);
    }
    

    private ResponseEntity<Map<String,String>> validate(boolean isSuccessful){
        if (isSuccessful) {
            Map<String,String> response = new HashMap<>();
            response.put("message","Succesful" );
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", SQLException.class.toString());
            return ResponseEntity.ok(errorResponse);    
        }
    }

    @PostMapping("/edit-jadwal-dokter")
    public ResponseEntity<Map<String, String>> editJadwalDokter(@RequestBody Map<String, Object> data) {
    long idJadwal = Long.parseLong((String) data.get("idJadwal"));
    String originalHari = (String) data.get("hari");
    int originalJamMulai = Integer.parseInt((String) data.get("jamMulai"));
    int originalJamSelesai = Integer.parseInt((String) data.get("jamSelesai"));
    String editHari = (String) data.get("hari");
    int editjamMulai = Integer.parseInt((String) data.get("jamMulai"));
    int editjamSelesai = Integer.parseInt((String) data.get("jamSelesai"));

    boolean isSuccessful = jadwalDokterRepo.changeJadwalDokterData (
        idJadwal, originalHari, originalJamMulai, originalJamSelesai, editHari, editjamMulai, editjamSelesai);
        return validate(isSuccessful);

    
    }

}
