package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Deprecated
    public Animal() {
    }

    public Animal(String name, Survey survey) {
        this.name = name;
        this.survey = survey;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Survey getSurvey() {
        return survey;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", survey=" + survey +
                '}';
    }

}
