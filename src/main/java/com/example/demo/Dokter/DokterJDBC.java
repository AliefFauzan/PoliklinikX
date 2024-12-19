package com.example.demo.Dokter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.PenggunaService;

@Repository
public class DokterJDBC implements DokterRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PenggunaService service;

    @Override
    public Optional<DokterModel> validateUser (String username, String password) {
        password = service.getPasswordEncoder().encode(password);
        String sql = "select * from Dokter WHERE username LIKE ? AND password LIKE ? ";
        List<DokterModel> Dokter = jdbcTemplate.query(sql, this::mapRowToDokter ,username,password); 
        return Dokter.size() == 0 ? Optional.empty() : Optional.of(Dokter.get(0));
    }

    private DokterModel mapRowToDokter(ResultSet rSet, int rowNum)throws SQLException {
        return new DokterModel(
     
            rSet.getInt("idPegawai"),
            rSet.getString("username"),
            rSet.getString("password"),
            rSet.getString("nama"),
            rSet.getString("spesialisasi"),
            rSet.getInt("kuotaPasien"),
            rSet.getInt("tarif")
  
        );
    }
    
    @Override
    public List<DokterModel> findAllDokter() {
        String sql = "SELECT idPegawai, username, password, nama, spesialisasi, kuotaPasien, tarif FROM Dokter"; // SQL query to select all data from the Dokter table
        return jdbcTemplate.query(sql, this::mapRowToDokter); // Use the DokterRowMapper to map the rows
    }

    // Method to find a DokterModel by nama
    @Override
    public List<DokterModel> findDokterByNama(String nama) {
    String sql = "SELECT idPegawai, username, password, nama, spesialisasi, kuotaPasien, tarif FROM Dokter WHERE nama = ?"; // SQL query to find a doctor by nama
    return jdbcTemplate.query(sql, this::mapRowToDokter, nama); // Use queryForObject to retrieve a single result
}


    @Override
    public boolean register(String username, String nama, String password, String confPassword) {
        String sql = "select * from Dokter where username LIKE ?";
        List <DokterModel> Dokter = jdbcTemplate.query(sql,this::mapRowToDokter, username);
        boolean isAvailable = Dokter.size() > 0 ? false : true;

        boolean isSuccess = false;
        if (isAvailable) {
            boolean samePass = password.equals(confPassword);
            if (samePass) {
                String idPegawai = generateIDDokter();
                password = service.getPasswordEncoder().encode(password);
                sql = "insert into Dokter (idPegawai, username, password, nama) VALUES (?,?,?,?) ";
                jdbcTemplate.update(idPegawai, username, password, nama);
                isSuccess=true;
            }    
        }
        return isSuccess;
    }

    public String generateIDDokter() {
        String sql = "SELECT MAX(idPegawai) FROM Dokter";
        String lastNoRekamMedis = jdbcTemplate.queryForObject(sql, String.class);
    
        if (lastNoRekamMedis == null || lastNoRekamMedis.isEmpty()) {
            lastNoRekamMedis = "10000000"; // Start with "1" and 8 digits
        }
    
        // Extract the numeric part of the ID, increment it, and ensure the result starts with "1"
        int newNoRekamMedis = Integer.parseInt(lastNoRekamMedis) + 1;
    
        return String.valueOf(newNoRekamMedis);
    }
    

    @Override
    public boolean login(String username, String password) {
        String sql = "select * from Dokter WHERE username LIKE ?";
        List<DokterModel> Dokter = jdbcTemplate.query(sql, this::mapRowToDokter, username);
        // List<DokterModel> Dokter = jdbcTemplate.query(sql, this::mapRowToDokter, username);
     
        if(!Dokter.isEmpty()){
            String passwordFromQ = Dokter.get(0).getPassword().trim();
        
            if(service.getPasswordEncoder().matches(password, passwordFromQ)){
                return true;
            }
        }

        return  false ;
    }

    @Override
    public List<DokterModel> findDokterById(int idPegawai) {
    String sql = "SELECT idPegawai, username, password, spesialisasi, kuotaPasien, tarif FROM Dokter WHERE idPegawai = ?"; // SQL query to find a doctor by nama
    return jdbcTemplate.query(sql, this::mapRowToDokter, idPegawai); // Use queryForObject to retrieve a single result
}


}
