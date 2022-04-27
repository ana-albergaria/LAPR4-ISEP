package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import java.io.Serializable;

public class Barcode implements ValueObject, Serializable {
    public Barcode(String barcode) {
        throw new IllegalStateException("to develop");
    }

    public static Barcode valueOf(String barcode) {
        throw new IllegalStateException("to develop");
    }
}
