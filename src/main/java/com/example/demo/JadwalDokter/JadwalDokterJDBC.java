package com.example.demo.JadwalDokter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Dokter.Dokter;
import com.example.demo.Dokter.DokterModel;


@Repository
public class JadwalDokterJDBC implements JadwalDokterRepo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Dokter> getAllDokters() {
        String sql = "SELECT * FROM Dokter";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dokter.class));
    }

    @Override
    public void addJadwalDokter(int idDokter, String hari, int jamMulai, int jamSelesai) {
        String sql = "INSERT INTO JadwalDokter(idDokter, hari, jamMulai, jamSelesai) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, idDokter, hari, jamMulai, jamSelesai);
        
    }

    public void updateJadwalDokter(int idJadwal, String hari, int jamMulai, int jamSelesai) {
        String sql = "UPDATE JadwalDokter SET hari = ?, jamMulai = ?, jamSelesai = ? WHERE idJadwal = ?";
        jdbcTemplate.update(sql, hari, jamMulai, jamSelesai, idJadwal);
    }

    @Override
    public List<DokterModel> findAllDokter() {
        String sql = "SELECT * FROM Dokter";
        return jdbcTemplate.query(sql, this::mapRowToDokter);
    }

    private DokterModel mapRowToDokter(ResultSet rSet, int rowNum)throws SQLException{
        return new DokterModel(
            rSet.getLong("idPegawai"),
            rSet.getString("username"),
            rSet.getString("password"),
            rSet.getString("nama"),
            rSet.getString("spesialisasi"),
            rSet.getInt("kuotaPasien"),
            rSet.getInt("tarif")

        );
    }

    @Override
    public List<JadwalDokterModel> findAllJadwalDokter() {
       String sql = """
            SELECT 
                jd.idJadwal, 
                jd.idDokter, 
                d.nama AS nama, 
                d.spesialisasi AS spesialisasi, 
                jd.hari, 
                jd.jamMulai, 
                jd.jamSelesai
            FROM 
                JadwalDokter jd
            JOIN 
                Dokter d ON jd.idDokter = d.idPegawai
        """;

        return jdbcTemplate.query(sql, this::mapRowToJadwalDokter);
    }


    private JadwalDokterModel mapRowToJadwalDokter(ResultSet rSet, int rowNum)throws SQLException{
        return new JadwalDokterModel(
            rSet.getLong("idJadwal"),
            rSet.getLong("idDokter"),
            rSet.getString("nama"),
            rSet.getString("spesialisasi"),
            rSet.getString("hari"),
            rSet.getInt("jamMulai"),
            rSet.getInt("jamSelesai")


        );
    }

    // @Override
    // public boolean addJadwalDokterData(DokterModel dokter, String spesialisasi, String hari, String jamMulai, String jamSelesai) {
     
    //     String sql = """
    //     INSERT INTO JadwalDokter (idDokter, nama, spesialisasi, hari, jamMulai, jamSelesai)
    //     VALUES (?, ?, ?, ?, ?, ?)
    // """;

    // int isSuccess = jdbcTemplate.update(sql,
    //     dokter.getIdPegawai(),
    //     dokter.getNama(),
    //     spesialisasi,
    //     hari,
    //     jamMulai,
    //     jamSelesai
    // );

    // return isSuccess>0 ? true : false;
    // }

    @Override
    public List<JadwalDokterModel> findJadwalDokterBySpesialisasi(String spesialisasi) {
        String sql = """
            SELECT 
                jd.idJadwal, 
                jd.idDokter, 
                d.nama AS nama, 
                d.spesialisasi AS spesialisasi, 
                jd.hari, 
                jd.jamMulai, 
                jd.jamSelesai
            FROM 
                JadwalDokter jd
            JOIN 
                Dokter d ON jd.idDokter = d.idPegawai
            WHERE 
                d.spesialisasi = ?
        """;
    
        return jdbcTemplate.query(sql, this::mapRowToJadwalDokter, spesialisasi);
    }

    public List<JadwalDokterModel> findJadwalDokterByNamaAndSpesialisasi(String nama, String spesialisasi) {
        String sql = """
            SELECT 
                jd.idJadwal, 
                jd.idDokter, 
                d.nama AS nama, 
                d.spesialisasi AS spesialisasi, 
                jd.hari, 
                jd.jamMulai, 
                jd.jamSelesai
            FROM 
                JadwalDokter jd
            JOIN 
                Dokter d ON jd.idDokter = d.idPegawai
            WHERE 
                d.nama LIKE ? AND d.spesialisasi = ?
        """;
    
        return jdbcTemplate.query(sql, this::mapRowToJadwalDokter, "%" + nama + "%", spesialisasi);
    }


//     @Override
//     public boolean changeJadwalDokterData(
//         JadwalDokterModel jadwal,
//         String originalHari,
//         int originalJamMulai,
//         int originalJamSelesai,
//         String editHari,
//         int editJamMulai,
//         int editJamSelesai
// )  {
//     String sql = """
//         UPDATE JadwalDokter
//         SET 
//             hari = ?, 
//             jamMulai = ?, 
//             jamSelesai = ?
//         WHERE 
//             idJadwal = ? AND 
//             hari = ? AND 
//             jamMulai = ? AND 
//             jamSelesai = ?
//     """;
//         int isSuccessful = jdbcTemplate.update(sql, jadwal.getIdJadwal(), originalHari, originalJamMulai, originalJamSelesai, editHari, editJamMulai, editJamSelesai);



//         return isSuccessful > 0 ? true : false;
//     }



    





}