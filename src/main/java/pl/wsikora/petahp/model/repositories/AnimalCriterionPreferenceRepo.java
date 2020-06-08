package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.AnimalCriterionPreference;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;


public interface AnimalCriterionPreferenceRepo extends JpaRepository<AnimalCriterionPreference, Long> {

    @Query(value = "select acp.weight from AnimalCriterionPreference acp where acp.survey = ?1 and acp.evaluator = ?2 and acp.animal = ?3")
    List<Double> getAllWeightsBySurveyIdAndEvaluatorIdAndAnimalId (Survey survey, Evaluator evaluator, Animal animal);

    @Query(nativeQuery = true,
           value ="select acp.weight from animals_criteria_preferences acp where acp.survey_id = ?1 and acp.evaluator_id = ?2 order by acp.animal_id, acp.criterion_id")
    List<Double> getAllWeightBySurveyAndEvaluator(long surveyId, long evaluatorId);

}
