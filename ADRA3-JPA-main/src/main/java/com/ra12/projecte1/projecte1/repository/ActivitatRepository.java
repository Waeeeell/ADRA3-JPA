package com.ra12.projecte1.projecte1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.ra12.projecte1.projecte1.model.Activitat;

@Repository
public interface ActivitatRepository extends CrudRepository<Activitat, Long> {
    Optional<Activitat> findById(Long id);
}