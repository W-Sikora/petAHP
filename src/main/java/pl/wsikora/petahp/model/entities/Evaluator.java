package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "evaluators")
public class Evaluator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double weight;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Deprecated
    public Evaluator() {
    }

    public Evaluator(String name, double weight, Survey survey) {
        this.name = name;
        this.weight = weight;
        this.survey = survey;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public Survey getSurvey() {
        return survey;
    }

    @Override
    public String toString() {
        return "Evaluator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", survey=" + survey +
                '}';
    }

}
