package eapli.base.warehousemanagement.domain;

import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Length {
    private static final long serialVersionUID = 1L;

    private Long length;

    public Length(Long length){
        Preconditions.isPositive(length, "Length must be positive!");

        this.length=length;
    }

    public Length() {
        this.length= null;
    }

    public static  Length valueOf(final Long length){
        return new Length(length);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Length)) {
            return false;
        } else {
            Length that = (Length) o;
            return Objects.equals(this.length, that.length);
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.length).code();
    }

    @Override
    public String toString() {
        return String.valueOf(this.length);
    }
}
