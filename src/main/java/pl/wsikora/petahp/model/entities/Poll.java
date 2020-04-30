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
    private boolean visibility;
    private String link;
    @Column(name = "access_code")
    private int accessCode;

    public Poll() {
    }

    public Poll(User user, String name, LocalDateTime creationDate, LocalDate endDate, int noOfVoters, boolean visibility, String link, int accessCode) {
        this.user = user;
        this.name = name;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.noOfVoters = noOfVoters;
        this.visibility = visibility;
        this.link = link;
        this.accessCode = accessCode;
    }

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
        visibility = true;
        link = linkGenerator();
        accessCode = accessCodeGenerator();
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

    private int accessCodeGenerator() {
        Random random = new Random();
        return random.nextInt((999 - 100) + 1) + 100;
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

    public String getLink() {
        return link;
    }

    public int getAccessCode() {
        return accessCode;
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
                ", link='" + link + '\'' +
                ", accessCode=" + accessCode +
                '}';
    }
}
