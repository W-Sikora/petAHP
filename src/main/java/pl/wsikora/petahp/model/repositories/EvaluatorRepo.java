package pl.wsikora.petahp.model.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface EvaluatorRepo extends JpaRepository<Evaluator, Long> {

    @Query(nativeQuery = true, value = "select * from evaluators e where e.survey_id = ?1 and e.name is not null")
    List<Evaluator> findAllWithNotNullNameBySurvey(Survey survey);

}
