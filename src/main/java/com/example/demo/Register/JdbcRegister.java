package com.example.demo.Register;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRegister implements RegisterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void tambahUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getNama();
        String peran = "member";

        String noRekamMedis = RekamMedisGenerator.generateNoRekamMedis();

        String sql = "INSERT INTO Pasien (username, password, nama, roles, norekammedis) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, username, password, name, peran, noRekamMedis);

    }
}