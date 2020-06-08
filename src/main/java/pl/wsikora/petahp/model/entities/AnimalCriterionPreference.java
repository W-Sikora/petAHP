package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "animals_criteria_preferences")
public class AnimalCriterionPreference {

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

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public AnimalCriterionPreference() {
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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "AnimalCriterionPreference{" +
                "id=" + id +
                ", weight=" + weight +
                ", survey=" + survey +
                ", evaluator=" + evaluator +
                ", criterion=" + criterion +
                ", animal=" + animal +
                '}';
    }
}
