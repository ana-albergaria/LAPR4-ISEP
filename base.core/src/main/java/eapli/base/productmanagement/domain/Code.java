package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;

public class Code implements ValueObject, Serializable, Comparable<Code> {
    private static final long serialVersionUID = 1L;

    public static Code valueOf(String uniqueInternalCode) {
        throw new IllegalStateException("to develop");
    }

    @Override
    public int compareTo(Code o) {
        throw new IllegalStateException("to develop");
    }
}
