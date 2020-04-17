package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.wsikora.petahp.model.entities.Poll;


public interface PollRepository extends JpaRepository<Poll, Long> {

}
