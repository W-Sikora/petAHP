package pl.wsikora.petahp.model.entities;

import pl.wsikora.petahp.utils.Builder;

import javax.persistence.*;

@Entity
@Table(name = "criteria_preferences")
public class CriterionPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double weight;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "evaluator_id")
    private Evaluator evaluator;

    @ManyToOne
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;

    @Deprecated
    public CriterionPreference() {
    }

    public static class CriterionPreferenceBuilder extends Builder<CriterionPreference> {

        private CriterionPreferenceBuilder() {
            super(new CriterionPreference());
        }

        public CriterionPreferenceBuilder withId(long id) {
            add(e -> e.id = id);
            return this;
        }

        public CriterionPreferenceBuilder withWeight(double weight) {
            add(e -> e.weight = weight);
            return this;
        }

        public CriterionPreferenceBuilder withSurvey(Survey survey) {
            add(e -> e.survey = survey);
            return this;
        }

        public CriterionPreferenceBuilder withEvaluator(Evaluator evaluator) {
            add(e -> e.evaluator = evaluator);
            return this;
        }

        public CriterionPreferenceBuilder withCriterion(Criterion criterion) {
            add(e -> e.criterion = criterion);
            return this;
        }

    }

    public long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public Survey getSurvey() {
        return survey;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    @Override
    public String toString() {
        return "CriterionPreference{" +
                "id=" + id +
                ", weight=" + weight +
                ", survey=" + survey +
                ", evaluator=" + evaluator +
                ", criterion=" + criterion +
                '}';
    }

}
