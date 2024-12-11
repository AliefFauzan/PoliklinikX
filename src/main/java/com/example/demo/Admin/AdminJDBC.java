package com.example.demo.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.PenggunaService;

@Repository
public class AdminJDBC implements AdminRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PenggunaService service;

    @Override
    public Optional<AdminModel> validateUser (String username, String password) {
        password = service.getPasswordEncoder().encode(password);
        String sql = "select * from Admin WHERE username LIKE ? AND password LIKE ? ";
        List<AdminModel> Admin = jdbcTemplate.query(sql, this::mapRowToAdmin ,username,password); 
        return Admin.size() == 0 ? Optional.empty() : Optional.of(Admin.get(0));
    }

    private AdminModel mapRowToAdmin(ResultSet rSet, int rowNum)throws SQLException {
        return new AdminModel(
     
            rSet.getLong("idPegawai"),
            rSet.getString("username"),
            rSet.getString("password"),
            rSet.getString("nama")
  
        );
    }

    @Override
    public boolean register(String username, String nama, String password, String confPassword) {
        String sql = "select * from Admin where username LIKE ?";
        List <AdminModel> Admin = jdbcTemplate.query(sql,this::mapRowToAdmin, username);
        boolean isAvailable = Admin.size() > 0 ? false : true;

        boolean isSuccess = false;
        if (isAvailable) {
            boolean samePass = password.equals(confPassword);
            if (samePass) {
                String idPegawai = generateIDAdmin();
                password = service.getPasswordEncoder().encode(password);
                sql = "insert into Admin (idPegawai, username, password, nama) VALUES (?,?,?,?) ";
                jdbcTemplate.update(idPegawai, username, password, nama);
                isSuccess=true;
            }    
        }
        return isSuccess;
    }

    public String generateIDAdmin() {
        String sql = "SELECT MAX(idPegawai) FROM Admin";
        String lastNoRekamMedis = jdbcTemplate.queryForObject(sql, String.class);
    
        if (lastNoRekamMedis == null || lastNoRekamMedis.isEmpty()) {
            lastNoRekamMedis = "10000000"; // Start with "1" and 8 digits
        }
    
        // Extract the numeric part of the ID, increment it, and ensure the result starts with "1"
        long newNoRekamMedis = Long.parseLong(lastNoRekamMedis) + 1;
    
        return String.valueOf(newNoRekamMedis);
    }
    

    @Override
    public boolean login(String username, String password) {
        String sql = "select * from Admin WHERE username LIKE ?";
        List<String> Admin = jdbcTemplate.queryForList(sql, String.class, username);
        // List<AdminModel> Admin = jdbcTemplate.query(sql, this::mapRowToAdmin, username);
     
        if(!Admin.isEmpty()){
            String passwordFromQ = Admin.get(0);
        
            if(service.getPasswordEncoder().matches(password, passwordFromQ)){
                return true;
            }
        }

        return  false ;
    }


}
