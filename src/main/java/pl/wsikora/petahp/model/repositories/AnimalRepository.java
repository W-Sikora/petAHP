package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsikora.petahp.model.entities.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
