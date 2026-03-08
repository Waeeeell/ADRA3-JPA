package com.ra12.projecte1.projecte1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ra12.projecte1.projecte1.dto.RutaDTO;
import com.ra12.projecte1.projecte1.model.Activitat;
import com.ra12.projecte1.projecte1.repository.ActivitatRepository;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class ActivitatServices {

    @Autowired
    private ActivitatRepository activitatRepository;

    // [b1] Crea un nou registre utilitzant el DTO
    public Activitat save(RutaDTO dto) {
        Activitat activitat = new Activitat();
        activitat.setNombreRuta(dto.getNombreRuta());
        activitat.setDescripcio(dto.getDescripcio());
        activitat.setDias(dto.getDias());
        activitat.setHoras(dto.getHoras());
        activitat.setMinuts(dto.getMinuts());
        activitat.setDistancia(dto.getDistancia());

        System.out.println("Log [Integrant 1]: Guardant nova ruta: " + dto.getNombreRuta());
        return activitatRepository.save(activitat);
    }

    public Iterable<Activitat> findAll() {

        System.out.println("Log [Integrant 1]: Recuperant tots els registres");
        return activitatRepository.findAll();
    }

    public Optional<Activitat> findById(Long id) {

        System.out.println("Log [Integrant 1]: Buscant registre amb ID: " + id);
        return activitatRepository.findById(id);
    }

    public void importarCSV(MultipartFile file) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(",");
            Activitat a = new Activitat();
            a.setNombreRuta(d[0].trim());
            a.setDescripcio(d[1].trim());
            a.setDias(Integer.parseInt(d[2].trim()));
            a.setHoras(Integer.parseInt(d[3].trim()));
            a.setMinuts(Integer.parseInt(d[4].trim()));
            a.setDistancia(Integer.parseInt(d[5].trim()));
            activitatRepository.save(a);
        }

        System.out.println("Log [Integrant 1]: CSV processat correctament");
    }
}