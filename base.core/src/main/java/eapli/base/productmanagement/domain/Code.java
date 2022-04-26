package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;

public class Code implements ValueObject, Serializable, Comparable<Code> {
    private static final long serialVersionUID = 1L;

    @Override
    public int compareTo(Code o) {
        return 0; //to develop
    }
}
