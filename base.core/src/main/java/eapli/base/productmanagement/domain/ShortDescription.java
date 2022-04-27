package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;

public class ShortDescription implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    public static ShortDescription valueOf(String shortDescription) {
        throw new IllegalStateException("to develop");
    }
}
