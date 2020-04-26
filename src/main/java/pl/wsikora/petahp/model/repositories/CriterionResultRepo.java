package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Criterion;
import pl.wsikora.petahp.model.entities.CriterionResult;

import java.util.List;

public interface CriterionResultRepo extends JpaRepository<CriterionResult, Long> {

}
