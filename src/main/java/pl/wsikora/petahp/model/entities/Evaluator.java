package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "evaluators")
public class Evaluator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Double weight;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public Evaluator() {
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Override
    public String toString() {
        return "Evaluator{" +
                "id=" + id +
                ", name='" + name +
                ", weight=" + weight +
                ", survey=" + survey +
                '}';
    }
}
