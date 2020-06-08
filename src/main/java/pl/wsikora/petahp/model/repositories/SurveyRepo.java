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

    @Query("select s from Survey s where s.id = ?1")
    Survey findSurveyById(long id);

    @Query("select s from Survey s where s.votingLink = ?1")
    Survey findByVotingLink(String votingLink);

    @Query("select s from Survey s where s.resultLink = ?1")
    Survey findByResultLink(String resultLink);

    @Query("select s from Survey s where s.user = ?1 and s.status = ?2")
    List<Survey> findAllByUserAndStatus(User user, Status status);

    @Query("select count(s) from Survey s where s.user = ?1 and s.status = ?2")
    int countAllByUserAndStatus(User user, Status status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Survey s set s.status = ?1 where s.id = ?2")
    void updateStatus(Status status, long surveyId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Survey s set s.actualVotesNumber = ?1 where s.id = ?2")
    void updateActualVotesNumber(int votesNumber, long surveyId);

}
