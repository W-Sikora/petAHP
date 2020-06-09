package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.EvaluatorResult;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;
import java.util.Map;

public interface EvaluatorResultRepo extends JpaRepository<EvaluatorResult, Long> {

    @Query("select er from EvaluatorResult er where er.evaluator = ?1")
    EvaluatorResult findByEvaluator(Evaluator evaluator);

    @Query(nativeQuery = true, value = "select * from e")
    EvaluatorResult findByEvaluatorId(long id);

    @Query("select a.name from EvaluatorResult e join Animal a on e.animal.id = a.id where e.evaluator = ?1")
    List<String> findAllNamesByEvaluator(Evaluator evaluator);

    @Query("select e.value from EvaluatorResult e where e.evaluator = ?1 order by e.evaluator.id")
    List<Double> findAllValuesByEvaluator(Evaluator evaluator);

    @Query("select er.value from EvaluatorResult er where er.survey = ?1 order by er.evaluator.id")
    List<Double> findAllValuesBySurvey(Survey survey);


}
