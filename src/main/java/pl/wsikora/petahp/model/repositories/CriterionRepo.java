package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Criterion;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface CriterionRepo extends JpaRepository<Criterion, Long> {

    Criterion findByName(String name);

    List<Criterion> findAllBySurvey (Survey survey);
}
