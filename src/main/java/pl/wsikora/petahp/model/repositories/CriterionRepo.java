package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Poll;

import java.util.List;

public interface AnimalRepo extends JpaRepository<Animal, Long> {



}
