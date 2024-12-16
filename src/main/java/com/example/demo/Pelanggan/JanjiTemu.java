package com.example.demo.Pelanggan;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class JanjiTemu {

    private String date;
    private String time;
    private Long doctor;

    // Lombok akan otomatis menghasilkan getter dan setter untuk properti di atas
}
