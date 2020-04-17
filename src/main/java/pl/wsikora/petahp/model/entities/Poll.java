package pl.wsikora.petahp.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn
    private User creator;
    private String name;
    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
    @Column(name = "date_to_vote")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateToVote;
    @Column(name = "number_of_votes")
    private Integer numberOfVotes;

    public Poll() {
    }

    public Poll(User creator, String name, LocalDate creationDate, LocalDate dateToVote, Integer numberOfVotes) {
        this.creator = creator;
        this.name = name;
        this.creationDate = creationDate;
        this.dateToVote = dateToVote;
        this.numberOfVotes = numberOfVotes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDateToVote() {
        return dateToVote;
    }

    public void setDateToVote(LocalDate dateToVote) {
        this.dateToVote = dateToVote;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", creator=" + creator +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", dateToVote=" + dateToVote +
                ", numberOfVotes=" + numberOfVotes +
                '}';
    }
}
