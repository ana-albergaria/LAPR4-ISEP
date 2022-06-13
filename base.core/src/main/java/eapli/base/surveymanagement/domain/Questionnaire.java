package eapli.base.surveymanagement.domain;


import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * A Questionnaire.
 *
 * @author Marta Ribeiro 1201592
 */
@Entity
public class Questionnaire implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @Column(name = "questionnaire_code")
    private String code;

    private String title;

    private String welcomeMessage;

    @Column(length = 2000)
    private String questionnaireContent;

    private String finalMessage;

    //list<Client> targetAudience
    //set<Rule> rules
    //createdOn
    //endDate -> opcional

    public Questionnaire(final String code, final String title, final String welcomeMessage, final String questionnaireContent, final String finalMessage){
        Preconditions.noneNull(code, title, questionnaireContent, finalMessage);
        this.code=code;
        this.title=title;
        this.welcomeMessage = welcomeMessage;
        this.questionnaireContent = questionnaireContent;
        this.finalMessage = finalMessage;
    }

    protected Questionnaire(){

    }

    public String code() {
        return code;
    }

    public String title() {
        return title;
    }

    public String welcomeMessage() {
        return welcomeMessage;
    }

    public String finalMessage() {
        return finalMessage;
    }

    public String content(){
        return questionnaireContent;
    }

    public QuestionnaireDTO toDTO(){
        return new QuestionnaireDTO(this.code, this.title, this.welcomeMessage, this.questionnaireContent, this.finalMessage);
    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Questionnaire)) {
            return false;
        }

        final var that = (Questionnaire) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public String identity() {
        return code;
    }


}
