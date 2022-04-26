package eapli.base.warehousemanagement.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Square {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private int lSquare;
    private int wSquare;

    public Square(int lSquare, int wSquare){
        Preconditions.isPositive(lSquare, "Length Square must be positive!");
        Preconditions.isPositive(wSquare, "Width Square must be positive!");
        Preconditions.noneNull(lSquare, wSquare);
        this.lSquare = lSquare;
        this.wSquare = wSquare;
    }

    protected Square(){}
}
