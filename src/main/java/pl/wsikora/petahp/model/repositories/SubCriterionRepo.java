package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Criterion;

public interface CriterionRepo extends JpaRepository<Criterion, Long> {



}
