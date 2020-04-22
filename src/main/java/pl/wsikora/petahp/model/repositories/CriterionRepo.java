package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Criterion;

import java.util.List;

public interface CriterionRepo extends JpaRepository<Criterion, Long> {

    @Query(nativeQuery = true, value = "select * from criteria where criteria.poll_id = ?1")
    List<Criterion> findAllByPollId(long pollId);

}
