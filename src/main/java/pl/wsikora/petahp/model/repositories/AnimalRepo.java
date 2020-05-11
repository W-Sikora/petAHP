package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Animal;
import pl.wsikora.petahp.model.entities.Poll;

import java.util.List;

public interface AnimalRepo extends JpaRepository<Animal, Long> {

    @Query(nativeQuery = true, value = "select * from animals a where a.poll_id = ?1")
    List<Animal> findAllByPollId(long pollId);

    @Query(nativeQuery = true, value = "select * from animals a where a.id = ?1")
    Animal findById(long id);

}
