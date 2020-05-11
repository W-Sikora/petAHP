package pl.wsikora.petahp.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User user;

    private String name;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "no_of_voters")
    private int noOfVoters;

    @Column(name = "actual_no_of_votes")
    private int actualNoOfVotes;

    private boolean visibility;

    private String link;

    public Poll() {
    }

    public Poll(User user, String name, LocalDateTime creationDate, LocalDate endDate, int noOfVoters, int actualNoOfVotes, boolean visibility, String link) {
        this.user = user;
        this.name = name;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.noOfVoters = noOfVoters;
        this.actualNoOfVotes = actualNoOfVotes;
        this.visibility = visibility;
        this.link = link;
    }

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        actualNoOfVotes = 0;
        visibility = true;
        link = linkGenerator();
    }

    private String linkGenerator() {
        Random random = new Random();
        String generatedString = random.ints(48, 122)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return "" + generatedString;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    public int getNoOfVoters() {
        return noOfVoters;
    }

    public int getActualNoOfVotes() {
        return actualNoOfVotes;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public String getLink() {
        return link;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setNoOfVoters(int noOfVoters) {
        this.noOfVoters = noOfVoters;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name +
                ", creationDate=" + creationDate +
                ", endDate=" + endDate +
                ", noOfVoters=" + noOfVoters +
                ", actualNoOfVotes=" + actualNoOfVotes +
                ", visibility=" + visibility +
                ", link='" + link + '}';
    }
}
