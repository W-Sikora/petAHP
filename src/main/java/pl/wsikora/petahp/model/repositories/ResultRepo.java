package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.EvaluatorResult;
import pl.wsikora.petahp.model.entities.Result;
import pl.wsikora.petahp.model.entities.Survey;

import java.util.List;

public interface ResultRepo extends JpaRepository<Result, Long> {

}
