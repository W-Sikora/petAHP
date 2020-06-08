package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsikora.petahp.model.entities.Criterion;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface CriterionRepo extends JpaRepository<Criterion, Long> {

    @Query("select c from Criterion c where c.id = ?1")
    Criterion findCriterionById(long id);

    @Query("select c from Criterion c where c.survey = ?1")
    List<Criterion> findAllBySurvey(Survey survey);

    @Query("select c from Criterion c where c.survey = ?1 and c.criterion = ?2")
    List<Criterion> findAllBySurveyAndCriterion(Survey survey, Criterion criterion);

    @Query("select c from Criterion c where c.survey = ?1 and c.hierarchyLevel = ?2")
    List<Criterion> findAllBySurveyAndHierarchyLevel(Survey survey, int hierarchyLevel);

    @Query("select c.criterion.id from Criterion c where c.survey = ?1 group by c.criterion.id having c.criterion.id is not null order by c.criterion.id")
    List<Long> findAllParentsIdBySurvey(Survey survey);

    @Query("select c from Criterion c where c.id in :ids")
    List<Criterion> findAllParentsBySurvey(List<Long> ids);

    @Query(nativeQuery = true,
            value = "select * from criteria where survey_id = ?1 and parent_id = ?2")
    List<Criterion> findAllBySurveyIdAndParentId(long surveyId, long parentId);

    @Query(nativeQuery = true,
            value = "select * from criteria c0 where id in (select id from criteria where id in (select id from criteria where survey_id = :surveyId except (select c.parent_id from criteria c where c.survey_id = :surveyId group by c.parent_id order by c.parent_id)) order by c0.id)")
    List<Criterion> findAllWithNoChildren(@Param("surveyId") long surveyId);

    @Query(nativeQuery = true,
            value = "select count (*) from criteria c0 where id in (select id from criteria where id in (select id from criteria where survey_id = :surveyId except (select c.parent_id from criteria c where c.survey_id = :surveyId group by c.parent_id order by c.parent_id)) order by c0.id)")
    int countAllWithNoChildren(@Param("surveyId") long surveyId);

    @Query(nativeQuery = true,
            value = "select c.parent_id from criteria c where c.survey_id = ?1 group by c.parent_id having c.parent_id is not null order by c.parent_id")
    List<Long> getAllParentId(long surveyId);

    @Query(nativeQuery = true,
            value = "select id from criteria where id in (select id from criteria where survey_id = :surveyId except (select c.parent_id from criteria c where c.survey_id = :surveyId group by c.parent_id order by c.parent_id)) order by id")
    List<Long> findAllWithNoChildrenId(@Param("surveyId") long surveyId);

}
