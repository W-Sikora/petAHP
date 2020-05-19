package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "preferences")
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double weight;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "evaluator_id")
    private Evaluator evaluator;

    @ManyToOne
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;

    public Preference() {
    }

    public Preference(Double weight, Survey survey, Evaluator evaluator,  Criterion criterion) {
        this.weight = weight;
        this.survey = survey;
        this.evaluator = evaluator;
        this.criterion = criterion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "id=" + id +
                ", weight=" + weight +
                ", survey=" + survey +
                ", evaluator=" + evaluator +
                ", criterion=" + criterion +
                '}';
    }
}
