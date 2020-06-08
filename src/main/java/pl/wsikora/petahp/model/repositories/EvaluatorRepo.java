package pl.wsikora.petahp.model.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.Status;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface EvaluatorRepo extends JpaRepository<Evaluator, Long> {

    @Query("select e from Evaluator e where e.id = ?1")
    Evaluator findEvaluatorById(long evaluatorId);

    @Query(nativeQuery = true,
           value = "select * from evaluators e where e.survey_id = ?1 and e.name is null limit 1;")
    Evaluator findFirstBySurveyAndNameNull(long surveyId);

    @Query("select e from Evaluator e where e.survey = ?1 and e.name is not null")
    List<Evaluator> findAllWithNotNullNameBySurvey(Survey survey);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Evaluator e set e.name = ?1 where e = ?2")
    void updateName(String name, Evaluator evaluator);

}
