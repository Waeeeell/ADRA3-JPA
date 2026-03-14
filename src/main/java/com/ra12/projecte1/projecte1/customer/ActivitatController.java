package com.ra12.projecte1.projecte1.customer;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ra12.projecte1.projecte1.model.Activitat;
import com.ra12.projecte1.projecte1.dto.RutaDTO;
import com.ra12.projecte1.projecte1.repository.ActivitatRepository;
import com.ra12.projecte1.projecte1.service.ActivitatServices;

@RestController
@RequestMapping("/api")
public class ActivitatController {

    @Autowired
    private ActivitatServices activitatService;

    @Autowired
    private ActivitatRepository activitatRepository;

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

    @PutMapping("/{id}")
    public ResponseEntity<Activitat> update(@PathVariable Long id, @RequestBody Activitat activitatActualizada) {
        return activitatRepository.findById(id)
            .map(existing -> {
                existing.setNombreRuta(activitatActualizada.getNombreRuta());
                existing.setDescripcio(activitatActualizada.getDescripcio());
                existing.setDias(activitatActualizada.getDias());
                existing.setHoras(activitatActualizada.getHoras());
                existing.setMinuts(activitatActualizada.getMinuts());
                existing.setDistancia(activitatActualizada.getDistancia());
                activitatRepository.save(existing);
                return ResponseEntity.ok(existing);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/DeleteActivitat/{id}")
    public String delete(@PathVariable Long id) {
        activitatService.deleteById(id);
        return "Activitat eliminada amb èxit";
    }

    @GetMapping("/GetActivitats/filtre")
    public List<Activitat> filtreByDistancia(@RequestParam int distanciaMin) {
        return activitatService.findByDistanciaGreaterThanEqual(distanciaMin);
    }
}