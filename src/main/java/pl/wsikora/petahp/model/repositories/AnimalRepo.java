package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface AnimalRepo  extends JpaRepository<Animal, Long> {

    List<Animal> findAllBySurvey (Survey survey);
}
