package com.example.demo.Pelanggan;

import java.util.List;

public interface JanjiTemuRepository {

    void save(JanjiTemu janjiTemu);

    List<JanjiTemu> findAll();

    JanjiTemu findById(Long id);

}
