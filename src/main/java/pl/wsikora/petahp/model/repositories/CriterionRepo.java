package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Criterion;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface CriterionRepo extends JpaRepository<Criterion, Long> {

    Criterion findByNameAndSurvey(String name, Survey survey);

    List<Criterion> findAllBySurvey (Survey survey);

    List<Criterion> findAllBySurveyAndCriterion(Survey survey, Criterion criterion);
}
