package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.SubCriterion;
import pl.wsikora.petahp.model.entities.SubCriterionResult;

import java.util.List;

public interface SubCriterionResultRepo extends JpaRepository<SubCriterionResult, Long> {

}
