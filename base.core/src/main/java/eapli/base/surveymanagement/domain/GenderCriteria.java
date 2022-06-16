package eapli.base.surveymanagement.domain;

import eapli.base.clientmanagement.domain.Client;

import javax.persistence.Enumerated;

public class GenderCriteria extends Criteria{

    private enum Gender {
        FEMININE, MASCULINE, OTHER;
    }

    @Enumerated
    Gender gender;

    public GenderCriteria(final Type type) {
        super(type);
    }

    @Override
    public boolean verifyCriteria(Client client) {
        return client.gender().name().equals(this.gender.name());
    }


}
