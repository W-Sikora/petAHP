package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.Poll;

import java.util.List;

@Repository
public interface EvaluatorRepo extends JpaRepository<Evaluator, Long> {

    @Query(nativeQuery = true, value = "select e.id from evaluators e where e.poll_id = ?1 and e.name is null")
    List<Long> findEmptyEvaluators(long pollId);

    @Query(nativeQuery = true, value = "select count(*) from evaluators e where e.poll_id = ?1 and e.name is null")
    int countEmptyEvaluators(long pollId);

    @Query(nativeQuery = true, value = "select count(*) from evaluators e where e.poll_id = ?1 and e.name is not null")
    int countEvaluators(long pollId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value ="update evaluators set name = ?1 where id = ?2")
    void updateName(String name, long id);

}
