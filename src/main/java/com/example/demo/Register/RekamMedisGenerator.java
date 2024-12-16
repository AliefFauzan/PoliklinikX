package com.example.demo.Register;

import java.util.Random;

public class RekamMedisGenerator {
    public static String generateNoRekamMedis() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Tambahkan 2 angka
        sb.append(random.nextInt(10)); // digit pertama
        sb.append(random.nextInt(10)); // digit kedua

        // Tambahkan 2 huruf (A-Z)
        sb.append((char) ('A' + random.nextInt(26))); // huruf pertama
        sb.append((char) ('A' + random.nextInt(26))); // huruf kedua

        return sb.toString(); // Menghasilkan string seperti "12AB"
    }
}
