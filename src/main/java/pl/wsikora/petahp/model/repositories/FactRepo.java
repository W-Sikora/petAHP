package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsikora.petahp.model.entities.Fact;

public interface FactRepo extends JpaRepository<Fact, Long> {
}
