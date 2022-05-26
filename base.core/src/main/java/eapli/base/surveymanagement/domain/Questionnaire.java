package eapli.base.surveymanagement.domain;

import eapli.base.productmanagement.domain.AlphaNumericCode;
import eapli.base.productmanagement.domain.Code;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "sections", joinColumns = {@JoinColumn(name = "questionnaire_code", referencedColumnName = "id")})
    @MapKeyColumn(name = "section")
    @Column(name = "question")
    private HashMap<String, String> sectionsAndQuestions = new HashMap<>();

    public Questionnaire(final String code, final String title, final String welcomeMessage, final HashMap<String,String> sectionsAndQuestions){
        this.code=code;
        this.title=title;
        this.welcomeMessage=welcomeMessage;
        this.sectionsAndQuestions=sectionsAndQuestions;

    }

    protected Questionnaire(){

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
        throw new IllegalArgumentException("to develop");
    }

    @Override
    public String identity() {
        return code;
    }


}
