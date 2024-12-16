package com.example.demo.Pasien;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class PasienModel {
    int noRekamMedis;
    String dataRekamMedis;
    String username;
    String password;
    String nama;
    String tanggalLahir;
    String alamat;
  
}
