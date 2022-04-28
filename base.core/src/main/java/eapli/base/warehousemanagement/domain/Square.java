package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Square implements AggregateRoot<Long>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private Long lSquare;
    @Transient
    private Long wSquare;

    public Square(Long lSquare, Long wSquare){
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
