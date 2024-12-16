// package com.example.demo.Pelanggan;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;
// import org.springframework.jdbc.core.RowMapper;

// import java.util.List;

// @Repository
// public class JdbcJanjiTemuRepository implements JanjiTemuRepository {

//     private final JdbcTemplate jdbcTemplate;

//     @Autowired
//     public JdbcJanjiTemuRepository(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     @Override
//     public void save(JanjiTemu janjiTemu) {
//         String sql = "INSERT INTO janji_temu (date, time, doctor) VALUES (?, ?, ?)";
//         jdbcTemplate.update(sql, janjiTemu.getDate(), janjiTemu.getTime(), janjiTemu.getDoctor());
//     }

//     @Override
//     public List<JanjiTemu> findAll() {
//         String sql = "SELECT * FROM janji_temu";
//         return jdbcTemplate.query(sql, janjiTemuRowMapper());
//     }

//     @Override
//     public JanjiTemu findById(Long id) {
//         String sql = "SELECT * FROM janji_temu WHERE id = ?";
//         return jdbcTemplate.queryForObject(sql, janjiTemuRowMapper(), id);
//     }



//     private RowMapper<JanjiTemu> janjiTemuRowMapper() {
//         return (rs, rowNum) -> {
//             JanjiTemu janjiTemu = new JanjiTemu();
//             janjiTemu.setDate(rs.getString("date"));
//             janjiTemu.setTime(rs.getString("time"));
//             janjiTemu.setDoctor(rs.getLong("doctor"));
//             return janjiTemu;
//         };
//     }
// }
