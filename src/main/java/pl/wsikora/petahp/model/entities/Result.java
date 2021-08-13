package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double value;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Deprecated
    public Result() {
    }

    public Result(double value, Animal animal, Survey survey) {
        this.value = value;
        this.animal = animal;
        this.survey = survey;
    }

    public long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Survey getSurvey() {
        return survey;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", value=" + value +
                ", animal=" + animal +
                ", survey=" + survey +
                '}';
    }

}
