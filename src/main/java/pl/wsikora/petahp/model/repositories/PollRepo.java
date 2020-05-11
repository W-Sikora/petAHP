package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wsikora.petahp.model.entities.Poll;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PollRepo extends JpaRepository<Poll, Long> {

    @Query(nativeQuery = true, value = "select count(*) from polls p where p.visibility = ?1 and p.creator = ?2")
    int countVisible(boolean visibility, long usersId);

    @Query(nativeQuery = true, value = "select * from polls p where p.visibility = ?1 and p.creator = ?2")
    List<Poll> getAllVisible(boolean visibility, long usersId);

    @Query(nativeQuery = true, value = "select * from polls p where p.id = ?1")
    Poll findById(long id);

    @Query(nativeQuery = true, value = "select p.id from polls p where p.creator = ?1")
    long findByCreator(long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Poll p set p.visibility = :visibility where p.id = :pollId")
    void updateVisibility(@Param("visibility") boolean visibility, @Param("pollId") long pollId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "update polls set actual_no_of_votes = ?1 where id = ?2")
    void updateVisibility(int nb, long id);

    @Query(nativeQuery = true, value = "select count(p.link) from polls p where p.link like ?1")
    int checkLink(String link);

    @Query(nativeQuery = true, value = "select p.end_date from polls p where p.id = ?1")
    LocalDate getEndDate(long id);

    @Query(nativeQuery = true, value = "select p.id from polls p where p.link like ?1")
    long getId(String link);

}
