package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Entity
public class Answer implements AggregateRoot<String>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    @Id
    @Column (name = "AnswerID")
    private String answerID;

    public Answer(String answerID){
        this.answerID = answerID;
    }

    public Answer() {

    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String identity() {
        return null;
    }
}
