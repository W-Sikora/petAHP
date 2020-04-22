package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.wsikora.petahp.model.entities.Poll;

import java.util.List;

public interface PollRepo extends JpaRepository<Poll, Long> {

    @Query(nativeQuery = true, value = "select * from polls where polls.visibility = ?1 and polls.user_id = ?2")
    List<Poll> findAllByVisibilityAndUsersId(boolean visibility, long usersId);

}
