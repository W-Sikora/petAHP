package pl.wsikora.petahp.model.entities;

import pl.wsikora.petahp.utils.Builder;

import javax.persistence.*;

@Entity
@Table(name = "evaluator_results")
public class EvaluatorResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double value;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToOne
    @JoinColumn(name = "evaluator_id")
    private Evaluator evaluator;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Deprecated
    public EvaluatorResult() {
    }

    public static class EvaluatorResultBuilder extends Builder<EvaluatorResult> {

        private EvaluatorResultBuilder() {
            super(new EvaluatorResult());
        }

        public EvaluatorResultBuilder withId(long id) {
            add(e -> e.id = id);
            return this;
        }

        public EvaluatorResultBuilder withValue(double value) {
            add(e -> e.value = value);
            return this;
        }

        public EvaluatorResultBuilder withAnimal(Animal animal) {
            add(e -> e.animal = animal);
            return this;
        }

        public EvaluatorResultBuilder withEvaluator(Evaluator evaluator) {
            add(e -> e.evaluator = evaluator);
            return this;
        }

        public EvaluatorResultBuilder withSurvey(Survey survey) {
            add(e -> e.survey = survey);
            return this;
        }

    }

    public static EvaluatorResultBuilder builder() {
        return new EvaluatorResultBuilder();
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

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public Survey getSurvey() {
        return survey;
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
