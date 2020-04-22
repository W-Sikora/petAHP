package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Criterion;
import pl.wsikora.petahp.model.entities.SubCriterion;

import java.util.List;

public interface SubCriterionRepo extends JpaRepository<SubCriterion, Long> {

    @Query(nativeQuery = true, value = "select * from sub_criteria where sub_criteria.poll_id = ?1")
    List<SubCriterion> findAllByPollId(long pollId);

}
