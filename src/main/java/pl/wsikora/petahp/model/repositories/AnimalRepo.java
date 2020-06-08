package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface AnimalRepo extends JpaRepository<Animal, Long> {

    @Query("select a from Animal a where a.id = ?1")
    Animal findAnimalById(long id);

    @Query("select a from Animal a where a.survey = ?1 order by a.id")
    List<Animal> findAllBySurvey(Survey survey);

    @Query("select count(a) from Animal a where a.survey = ?1")
    int countAllBySurvey(Survey survey);

}
