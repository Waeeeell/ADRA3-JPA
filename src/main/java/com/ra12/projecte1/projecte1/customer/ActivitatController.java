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

    @PutMapping("/PutActivitat/{id}")
    public ResponseEntity<Activitat> actualizarActivitat(@PathVariable Long id,
            @RequestBody Activitat activitatActualitzada) {
        Optional<Activitat> activitatExistente = activitatRepository.findById(id);

        if (activitatExistente.isPresent()) {
            Activitat activitat = activitatExistente.get();
            activitat.setNombreRuta(activitatActualitzada.getNombreRuta());
            activitat.setDescripcio(activitatActualitzada.getDescripcio());
            activitat.setDias(activitatActualitzada.getDias());
            activitat.setHoras(activitatActualitzada.getHoras());
            activitat.setMinuts(activitatActualitzada.getMinuts());
            activitat.setDistancia(activitatActualitzada.getDistancia());

            Activitat activitatGuardada = activitatRepository.save(activitat);
            return ResponseEntity.ok(activitatGuardada);
        } else {
            return ResponseEntity.notFound().build();
        }
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