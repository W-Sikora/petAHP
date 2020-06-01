package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.wsikora.petahp.model.entities.Status;
import pl.wsikora.petahp.model.entities.Survey;
import pl.wsikora.petahp.model.entities.User;

import java.util.List;

public interface SurveyRepo extends JpaRepository<Survey, Long> {

    List<Survey> findAllByUserAndStatus(User user, Status status);

    Survey findById(long id);
    Survey findByVotingLink(String votingLink);

    int countAllByUserAndStatus(User user, Status status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "update surveys set status = ?1 where id = ?2")
    void updateStatus(Status status, long survey_id);
}
