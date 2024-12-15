package com.example.demo.Pasien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.PenggunaService;

@Repository
public class PasienJDBC implements PasienRepo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PenggunaService service;

    @Override
    public Optional<PasienModel> validateUser (String username, String password) {
        password = service.getPasswordEncoder().encode(password);
        String sql = "select * from Pasien WHERE username LIKE ? AND password LIKE ? ";
        List<PasienModel> pasien = jdbcTemplate.query(sql, this::mapRowToPasien ,username,password); 
        return pasien.size() == 0 ? Optional.empty() : Optional.of(pasien.get(0));
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

    @Override
    public boolean register(String username, String nama, String password, String confPassword, String tanggalLahir, String alamat) {
        String sql = "select * from pasien where username LIKE ?";
        List <PasienModel> pasien = jdbcTemplate.query(sql,this::mapRowToPasien, username);
        boolean isAvailable = pasien.size() > 0 ? false : true;

        boolean isSuccess = false;
        if (isAvailable) {
            boolean samePass = password.equals(confPassword);
            if (samePass) {
                String noRekamMedis = generateNoRekamMedis();
                password = service.getPasswordEncoder().encode(password);
                sql = "insert into pengguna (noRekamMedis, datRekamMedis, username, password, nama, tanggalLahir, alamat) VALUES (?,?,?,?,?,?,?) ";
                jdbcTemplate.update(noRekamMedis, null, username, password, nama, tanggalLahir, alamat);
                isSuccess=true;
            }    
        }
        return isSuccess;
    }

    public String generateNoRekamMedis() {
        String sql = "SELECT MAX(noRekamMedis) FROM pengguna";
        Integer lastNoRekamMedis = jdbcTemplate.queryForObject(sql, Integer.class);

        if (lastNoRekamMedis == null) {
            lastNoRekamMedis = 0;
        }


        return String.format("%08d", lastNoRekamMedis + 1);  // 8-digit format
    }

    @Override
    public boolean login(String username, String password) {
        String sql = "select * from Pasien WHERE username LIKE ?";
        // List<String> pasien = jdbcTemplate.queryForList(sql, String.class, username);
        List<PasienModel> pasien = jdbcTemplate.query(sql, this::mapRowToPasien, username);
     
        if(!pasien.isEmpty()){
            String passwordFromQ = pasien.get(0).getPassword().trim();
        
            if(service.getPasswordEncoder().matches(password, passwordFromQ)){
                return true;
            }
        }

        return  false ;
    }

    @Override
    public void updateDataRekamMedis(int noRekamMedis, String dataRekamMedis) {
        String sql = "UPDATE Pasien SET dataRekamMedis = ? WHERE noRekamMedis = ?";
        jdbcTemplate.update(sql, dataRekamMedis, noRekamMedis);
    }
    
    // @Override
    // public List<PasienModel> findAllPasien() {
    //     String sql = """
    //         SELECT 
    //             p.noRekamMedis, 
    //             p.dataRekamMedis, 
    //             p.nama, 
    //             p.username
    //         FROM 
    //             Pasien p
    //     """;
    
    //     return jdbcTemplate.query(sql, this::mapRowToPasien);
    // }

    // private PasienModel mapRowToPasien(ResultSet rSet, int rowNum) throws SQLException {
    //     return new PasienModel(
    //         rSet.getInt("noRekamMedis"),
    //         rSet.getString("dataRekamMedis"),
    //         rSet.getString("nama"),
    //         rSet.getString("username")
    //     );
    // }
    


    

    

}
