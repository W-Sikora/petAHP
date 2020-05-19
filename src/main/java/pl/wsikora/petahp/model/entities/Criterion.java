package pl.wsikora.petahp.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "criteria")
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String details;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Criterion criterion;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public Criterion() {
    }

    public Criterion(String name, String details, Criterion criterion, Survey survey) {
        this.name = name;
        this.details = details;
        this.criterion = criterion;
        this.survey = survey;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Override
    public String toString() {
        return "Criterion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", parentCriterion=" + criterion +
                ", survey=" + survey +
                '}';
    }
}
