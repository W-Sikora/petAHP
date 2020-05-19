package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsikora.petahp.model.entities.Evaluator;

public interface EvaluatorRepo extends JpaRepository<Evaluator, Long> {
}
