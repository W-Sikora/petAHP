package pl.wsikora.petahp.model.entities;

import pl.wsikora.petahp.utils.Builder;

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

    public static class EvaluatorBuilder extends Builder<Evaluator> {

        private EvaluatorBuilder() {
            super(new Evaluator());
        }

        public EvaluatorBuilder withId(long id) {
            add(e -> e.id = id);
            return this;
        }

        public EvaluatorBuilder withName(String name) {
            add(e -> e.name = name);
            return this;
        }

        public EvaluatorBuilder withWeight(double weight) {
            add(e -> e.weight = weight);
            return this;
        }

        public EvaluatorBuilder withSurvey(Survey survey) {
            add(e -> e.survey = survey);
            return this;
        }

    }

    public static EvaluatorBuilder builder() {
        return new EvaluatorBuilder();
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
