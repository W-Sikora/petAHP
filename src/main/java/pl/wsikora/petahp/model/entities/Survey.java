package pl.wsikora.petahp.model.entities;

import org.springframework.format.annotation.DateTimeFormat;
import pl.wsikora.petahp.utils.Builder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private int evaluatorNumber;

    @Column(name = "actual_votes_number")
    private int actualVotesNumber;

    @Column(name = "voting_link")
    private String votingLink;

    @Column(name = "result_link")
    private String resultLink;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User user;

    @Deprecated
    public Survey() {
    }

    public static class SurveyBuilder extends Builder<Survey> {

        private SurveyBuilder() {
            super(new Survey());
        }

        public SurveyBuilder withId(long id) {
            add(e -> e.id = id);
            return this;
        }

        public SurveyBuilder withName(String name) {
            add(e -> e.name = name);
            return this;
        }

        public SurveyBuilder withCreationDate(LocalDateTime creationDate) {
            add(e -> e.creationDate = creationDate);
            return this;
        }

        public SurveyBuilder withEndDate(LocalDate endDate) {
            add(e -> e.endDate = endDate);
            return this;
        }

        public SurveyBuilder withEvaluatorNumber(int evaluatorNumber) {
            add(e -> e.evaluatorNumber = evaluatorNumber);
            return this;
        }

        public SurveyBuilder withActualVotesNumber(int actualVotesNumber) {
            add(e -> e.actualVotesNumber = actualVotesNumber);
            return this;
        }

        public SurveyBuilder withVotingLink(String votingLink) {
            add(e -> e.votingLink = votingLink);
            return this;
        }

        public SurveyBuilder withResultLink(String resultLink) {
            add(e -> e.resultLink = resultLink);
            return this;
        }

        public SurveyBuilder withStatus(Status status) {
            add(e -> e.status = status);
            return this;
        }

        public SurveyBuilder withUser(User user) {
            add(e -> e.user = user);
            return this;
        }

    }

    public static SurveyBuilder builder() {
        return new SurveyBuilder();
    }

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        status = Status.FOUNDED;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getEvaluatorNumber() {
        return evaluatorNumber;
    }

    public int getActualVotesNumber() {
        return actualVotesNumber;
    }

    public String getVotingLink() {
        return votingLink;
    }

    public String getResultLink() {
        return resultLink;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", endDate=" + endDate +
                ", evaluatorNumber=" + evaluatorNumber +
                ", actualVotesNumber=" + actualVotesNumber +
                ", votingLink='" + votingLink + '\'' +
                ", resultLink='" + resultLink + '\'' +
                ", status=" + status +
                ", user=" + user +
                '}';
    }

}
