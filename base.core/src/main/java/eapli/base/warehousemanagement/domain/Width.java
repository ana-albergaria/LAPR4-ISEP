package eapli.base.warehousemanagement.domain;

import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class Width {
    private static final long serialVersionUID = 1L;

    private int width;

    public Width(int width){
        Preconditions.isPositive(width, "Width must be positive!");

        this.width=width;
    }

    public Width() {
        this.width= 0;
    }

    public static  Width valueOf(final int width){
        return new Width(width);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Width)) {
            return false;
        } else {
            Width that = (Width) o;
            return this.width==that.width;
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.width).code();
    }

    @Override
    public String toString() {
        return String.valueOf(this.width);
    }
}
