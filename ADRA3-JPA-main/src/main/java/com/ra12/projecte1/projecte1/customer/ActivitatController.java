package com.ra12.projecte1.projecte1.customer;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ra12.projecte1.projecte1.model.Activitat;
import com.ra12.projecte1.projecte1.dto.RutaDTO;
import com.ra12.projecte1.projecte1.service.ActivitatServices;

@RestController
@RequestMapping("/api")
public class ActivitatController {

    @Autowired
    private ActivitatServices activitatService;

    @PostMapping("/PostActivitat/csv")
    public String uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            activitatService.importarCSV(file);
            return "CSV OK";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PostMapping("/PostActivitat/activitat")
    public Activitat create(@RequestBody RutaDTO rutaDTO) {
        return activitatService.save(rutaDTO);
    }

    @GetMapping("/GetAll/allActivitats")
    public Iterable<Activitat> getAll() {
        return activitatService.findAll();
    }

    @GetMapping("/GetActivitat/{id}")
    public Optional<Activitat> activitatById(@PathVariable Long id) {
        return activitatService.findById(id);
    }
}