package pl.wsikora.petahp.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private boolean visibility;

    public Poll() {
    }

    public Poll(User user, String name, LocalDateTime creationDate, LocalDate endDate, int noOfVoters, boolean visibility) {
        this.user = user;
        this.name = name;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.noOfVoters = noOfVoters;
        this.visibility = visibility;
    }

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        visibility = true;
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

    public boolean isVisibility() {
        return visibility;
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
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", endDate=" + endDate +
                ", noOfVoters=" + noOfVoters +
                ", visibility=" + visibility +
                '}';
    }
}
