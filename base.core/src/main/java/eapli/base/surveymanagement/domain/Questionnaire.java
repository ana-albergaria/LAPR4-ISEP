package eapli.base.surveymanagement.domain;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

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

    private String finalMessage;

    /*@ElementCollection
    @CollectionTable(name = "sections", joinColumns = {@JoinColumn(name = "questionnaire_code", referencedColumnName = "code")})
    @MapKeyColumn(name = "section")
    @Column(name = "question")
    private HashMap<String, String> sectionsAndQuestions = new HashMap<>();*/

    public Questionnaire(final String code, final String title, final String welcomeMessage, /*final HashMap<String,String> sectionsAndQuestions,*/ final String finalMessage){
        this.code=code;
        this.title=title;
        this.welcomeMessage=welcomeMessage;
        //this.sectionsAndQuestions=sectionsAndQuestions;
        this.finalMessage=finalMessage;
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

    /*public HashMap<String, String> sectionsAndQuestions() {
        return sectionsAndQuestions;
    }*/

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
