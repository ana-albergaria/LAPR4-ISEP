package eapli.base.surveymanagement.domain;

import eapli.base.clientmanagement.domain.Client;

import javax.persistence.Enumerated;

public class AgeCriteria extends Criteria{

    private Long age;

    private enum Signal {
        GREATER_THAN, LESS_THAN;
    }

    @Enumerated
    Signal signal;

    public AgeCriteria(final Type type){
        super(type);
    }

    @Override
    public boolean verifyCriteria(Client client) {
        if (this.signal.equals(Signal.GREATER_THAN)){
            return client.age()>this.age.intValue();
        }else{
            return client.age()<this.age.intValue();
        }
    }
}
