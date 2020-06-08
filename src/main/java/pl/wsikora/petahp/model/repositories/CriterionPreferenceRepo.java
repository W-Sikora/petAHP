package pl.wsikora.petahp.model.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsikora.petahp.model.entities.Criterion;
import pl.wsikora.petahp.model.entities.CriterionPreference;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface CriterionPreferenceRepo extends JpaRepository<CriterionPreference, Long> {

    @Query(nativeQuery = true,
            value = "select cp.weight from criteria_preferences cp where cp.survey_id = ?1 and cp.evaluator_id = ?2 and cp.criterion_id in ?3")
    List<Double> getAllWeightBySurveyAndEvaluator(long surveyId, long evaluatorId, List<Long> ids);

    @Query(nativeQuery = true,
            value = "select weight from criteria_preferences where survey_id = :surveyId and evaluator_id = :evaluatorId and criterion_id = :criterionId")
    double getParentWeight(@Param("surveyId") long surveyId, @Param("evaluatorId") long evaluatorId, @Param("criterionId") long criterionId);

    @Query(nativeQuery = true,
            value = "select cp.weight from criteria_preferences cp where evaluator_id = :evaluatorId and criterion_id in :ids order by cp.criterion_id")
    List<Double> getAllEssentialWeights(@Param("evaluatorId") long evaluatorId, @Param("ids") List<Long> ids);
}
