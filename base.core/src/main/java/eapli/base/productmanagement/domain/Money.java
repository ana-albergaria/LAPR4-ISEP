package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;
import java.util.Calendar;

public class Money implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    public Money onDate(Calendar date) {
        throw new IllegalStateException("to develop");
    }
}
