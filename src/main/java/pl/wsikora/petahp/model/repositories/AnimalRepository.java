package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllBySurvey(Survey survey);

    int countAllBySurvey(Survey survey);

}
