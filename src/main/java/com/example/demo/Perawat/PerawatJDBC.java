package com.example.demo.Perawat;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.PenggunaService;
import com.example.demo.Pasien.PasienModel;

@Repository
public class PerawatJDBC implements PerawatRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PenggunaService service;

    @Override
    public Optional<PerawatModel> validateUser (String username, String password) {
        password = service.getPasswordEncoder().encode(password);
        String sql = "select * from Perawat WHERE username LIKE ? AND password LIKE ? ";
        List<PerawatModel> Perawat = jdbcTemplate.query(sql, this::mapRowToPerawat ,username,password); 
        return Perawat.size() == 0 ? Optional.empty() : Optional.of(Perawat.get(0));
    }

    private PerawatModel mapRowToPerawat(ResultSet rSet, int rowNum)throws SQLException {
        return new PerawatModel(
     
            rSet.getLong("idPegawai"),
            rSet.getString("username"),
            rSet.getString("password"),
            rSet.getString("nama")
  
        );
    }

    @Override
    public boolean register(String username, String nama, String password, String confPassword) {
        String sql = "select * from Perawat where username LIKE ?";
        List <PerawatModel> Perawat = jdbcTemplate.query(sql,this::mapRowToPerawat, username);
        boolean isAvailable = Perawat.size() > 0 ? false : true;

        boolean isSuccess = false;
        if (isAvailable) {
            boolean samePass = password.equals(confPassword);
            if (samePass) {
                String idPegawai = generateIDPerawat();
                password = service.getPasswordEncoder().encode(password);
                sql = "insert into Perawat (idPegawai, username, password, nama) VALUES (?,?,?,?) ";
                jdbcTemplate.update(idPegawai, username, password, nama);
                isSuccess=true;
            }    
        }
        return isSuccess;
    }

    public String generateIDPerawat() {
        String sql = "SELECT MAX(idPegawai) FROM Perawat";
        String lastNoRekamMedis = jdbcTemplate.queryForObject(sql, String.class);
    
        if (lastNoRekamMedis == null || lastNoRekamMedis.isEmpty()) {
            lastNoRekamMedis = "30000000"; // Start with "3" and 8 digits
        }
    
        // Extract the numeric part of the ID, increment it, and ensure the result starts with "1"
        long newNoRekamMedis = Long.parseLong(lastNoRekamMedis) + 1;
    
        return String.valueOf(newNoRekamMedis);
    }
    

    @Override
    public boolean login(String username, String password) {
        String sql = "select * from Perawat WHERE username LIKE ?";
        List<String> Perawat = jdbcTemplate.queryForList(sql, String.class, username);
        // List<PerawatModel> Perawat = jdbcTemplate.query(sql, this::mapRowToPerawat, username);
     
        if(!Perawat.isEmpty()){
            String passwordFromQ = Perawat.get(0);
        
            if(service.getPasswordEncoder().matches(password, passwordFromQ)){
                return true;
            }
        }

        return  false ;
    }

    private PasienModel mapRowToPasien(ResultSet rSet, int rowNum)throws SQLException {
        return new PasienModel(
            rSet.getLong("noRekamMedis"),
            rSet.getLong("dataRekamMedis"),
            rSet.getString("username"),
            rSet.getString("password"),
            rSet.getString("nama"),
            rSet.getString("tanggalLahir"),
            rSet.getString("alamat")
        );
    }

    private PasienModel getPasienByNoRekamMedis(long noRekamMedis) {
        String sql = "SELECT * FROM pasien WHERE noRekamMedis = ?";
        List<PasienModel> pasien = jdbcTemplate.query(sql, this::mapRowToPasien, noRekamMedis);
        return pasien.isEmpty() ? null : pasien.get(0);
    }

    // @Override
    // public boolean manipulateDataRekamMedis(long noRekamMedis, String newDataRekamMedis) {
    //     // Retrieve the existing record.
    //     PasienModel pasien = getPasienByNoRekamMedis(noRekamMedis);
    //     if (pasien == null) {
    //         // No record found for the given noRekamMedis.
    //         return false;
    //     }

    //     // Manipulate dataRekamMedis as per the requirement.
    //     // Example: Update the field with a new value.
    //     String sql = "UPDATE pasien SET dataRekamMedis = ? WHERE noRekamMedis = ?";
    //     int rowsUpdated = jdbcTemplate.update(sql, newDataRekamMedis, noRekamMedis);

    //     return rowsUpdated > 0; // Return true if the update was successful.
    // }



}