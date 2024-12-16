package com.example.demo.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginUserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public String login(){
        return "Login/Login";
    }

    public List<LoginUserPengguna> findPengguna(String username) {
        String sql = """
            SELECT username, password, roles AS peran, nama 
            FROM (
                SELECT username, password, nama, roles FROM Pasien
                UNION ALL
                SELECT username, password, nama, roles FROM Perawat
                UNION ALL
                SELECT username, password, nama, roles FROM Dokter
                UNION ALL
                SELECT username, password, nama, roles FROM Administrasi
            ) AS users
            WHERE username = ?;
        """;
        return jdbcTemplate.query(sql, this::mapToPengguna, username);
    }
    

    private LoginUserPengguna mapToPengguna(ResultSet resultSet, int rowNum) throws SQLException {
        return new LoginUserPengguna(
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("nama"),
            resultSet.getString("peran") // Maps roles to peran
        );
    }
    

    @PostMapping
    public String validation(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        Model model, HttpSession session) {

        List<LoginUserPengguna> pengguna = findPengguna(username);

        if (pengguna.isEmpty()) {
            return "Login/Login";
        } else {
            String realPassword = pengguna.get(0).getPassword();
            if (realPassword.equals(password)) {    //Password benar
                // Simpan nama pengguna dalam session
                session.setAttribute("username", pengguna.get(0).getUsername());
                // Jika yang login adalah admin
                if(pengguna.get(0).getPeran().equals("admin")){
                    return "redirect:/Admin/SesudahLoginAdmin";
                }
                // Jika yang login adalah member
                else if(pengguna.get(0).getPeran().equals("dokter")){
                    return "redirect:/Dokter/SesudahLoginDokter";
                }else if(pengguna.get(0).getPeran().equals("perawat")){
                    return "redirect:/perawat/sesudahLoginPerawat";
                }else{
                    return "redirect:/home";
                }
            } 
            else {  //Password salah
                return "Login/Login";
            }
        }
    }
}