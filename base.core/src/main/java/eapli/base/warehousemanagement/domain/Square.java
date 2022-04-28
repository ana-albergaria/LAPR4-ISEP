package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Square implements AggregateRoot<Long>, Serializable {
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

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int compareTo(Long other) {
        return DomainEntities.hashCode(this);
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
