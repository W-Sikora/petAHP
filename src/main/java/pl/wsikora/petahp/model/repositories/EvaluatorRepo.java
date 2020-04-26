package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wsikora.petahp.model.entities.Evaluator;
import pl.wsikora.petahp.model.entities.Poll;

import java.util.List;

@Repository
public interface EvaluatorRepo extends JpaRepository<Evaluator, Long> {


}
