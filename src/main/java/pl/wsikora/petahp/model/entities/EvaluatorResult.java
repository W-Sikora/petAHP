package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "evaluator_results")
public class EvaluatorResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double value;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToOne
    @JoinColumn(name = "evaluator_id")
    private Evaluator evaluator;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public EvaluatorResult() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Override
    public String toString() {
        return "EvaluatorResult{" +
                "id=" + id +
                ", value=" + value +
                ", animal=" + animal +
                ", evaluator=" + evaluator +
                ", survey=" + survey +
                '}';
    }
}