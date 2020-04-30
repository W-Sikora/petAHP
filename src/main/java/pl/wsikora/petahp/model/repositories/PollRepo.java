package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wsikora.petahp.model.entities.Poll;

import java.util.List;

@Repository
public interface PollRepo extends JpaRepository<Poll, Long> {

    @Query(nativeQuery = true, value = "select count(*) from polls p where p.visibility = ?1 and p.creator = ?2")
    int countByVisibilityAndUserId(boolean visibility, long usersId);

    @Query(nativeQuery = true, value = "select * from polls p where p.visibility = ?1 and p.creator = ?2")
    List<Poll> findAllByVisibilityAndUsersId(boolean visibility, long usersId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Poll p SET p.visibility = :visibility WHERE p.id = :pollId")
    void updateVisibility(@Param("visibility") boolean visibility, @Param("pollId") long pollId);

    @Query(nativeQuery = true, value = "select count(p.link) from polls p where p.link like ?1")
    int checkLink(String link);


}
