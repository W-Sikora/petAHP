package pl.wsikora.petahp.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "evaluator_number")
    private Integer evaluatorNumber;

    @Column(name = "actual_votes_number")
    private Integer actualVotesNumber;

    @Column(name = "voting_link")
    private String votingLink;

    @Column(name = "result_link")
    private String resultLink;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User user;

    public Survey() {
    }

    public Survey(String name, LocalDateTime creationDate, LocalDate endDate, Integer evaluatorNumber, Integer actualVotesNumber, String votingLink, String resultLink, Status status, User user) {
        this.name = name;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.evaluatorNumber = evaluatorNumber;
        this.actualVotesNumber = actualVotesNumber;
        this.votingLink = votingLink;
        this.resultLink = resultLink;
        this.status = status;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        actualVotesNumber = 0;
        status = Status.FOUNDED;
        votingLink = linkGenerator();
        resultLink = linkGenerator();
    }

    private String linkGenerator() {
        Random random = new Random();
        return random.ints(48, 122)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getEvaluatorNumber() {
        return evaluatorNumber;
    }

    public void setEvaluatorNumber(Integer evaluatorNumber) {
        this.evaluatorNumber = evaluatorNumber;
    }

    public Integer getActualVotesNumber() {
        return actualVotesNumber;
    }

    public void setActualVotesNumber(Integer actualVotesNumber) {
        this.actualVotesNumber = actualVotesNumber;
    }

    public String getVotingLink() {
        return votingLink;
    }

    public void setVotingLink(String votingLink) {
        this.votingLink = votingLink;
    }

    public String getResultLink() {
        return resultLink;
    }

    public void setResultLink(String resultLink) {
        this.resultLink = resultLink;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
