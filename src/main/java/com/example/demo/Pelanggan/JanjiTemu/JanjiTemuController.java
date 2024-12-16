package com.example.demo.Pelanggan.JanjiTemu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
public class JanjiTemuController {

    private final DokterRepository dokterRepository;

    @Autowired
    public JanjiTemuController(DokterRepository dokterRepository) {
        this.dokterRepository = dokterRepository;
    }

    @GetMapping("/janjiTemu")
    public String getJanjiTemuPage(Model model) {
        List<Dokter> dokters = dokterRepository.getAllDokters();
        model.addAttribute("dokters", dokters);
        model.addAttribute("spesialisasiList", dokters);
        return "/Pelanggan/JanjiTemu";  // Thymeleaf template name
    }

    @PostMapping("/janjiTemu")
    public String submitJanjiTemu(@RequestParam("noRekamMedis") String noRekamMedis,
                                  @RequestParam("idDokter") int idDokter,
                                  @RequestParam("tanggal") Date tanggal,
                                  @RequestParam("waktu") Time waktu,
                                  @RequestParam("department") String department) {
        JanjiTemu janjiTemu = new JanjiTemu(0, noRekamMedis, idDokter, tanggal, waktu, department);
        dokterRepository.saveJanjiTemu(janjiTemu);
        return "redirect:/janjiTemu";  // Redirect to the form page after submission
    }
}
