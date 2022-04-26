package eapli.base.warehousemanagement.domain;

import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class SquareSize {
    private static final long serialVersionUID = 1L;

    private int squareSize;

    public SquareSize(int squareSize){
        Preconditions.isPositive(squareSize, "Square Size must be positive!");

        this.squareSize=squareSize;
    }

    public SquareSize() {this.squareSize= 0;}

    public static  SquareSize valueOf(final int squareSize){
        return new SquareSize(squareSize);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof SquareSize)) {
            return false;
        } else {
            SquareSize that = (SquareSize) o;
            return this.squareSize==that.squareSize;
        }
    }

    @Override
    public int hashCode() {
        return (new HashCoder()).with(this.squareSize).code();
    }

    @Override
    public String toString() {
        return String.valueOf(this.squareSize);
    }
}
