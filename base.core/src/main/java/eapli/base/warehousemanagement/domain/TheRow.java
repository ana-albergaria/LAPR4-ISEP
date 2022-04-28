package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class TheRow implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private Long rowID;

    @Transient
    @OneToOne(cascade = CascadeType.ALL)
    private Square beginSquare;

    @Transient
    @OneToOne(cascade = CascadeType.ALL)
    private Square endSquare;

    @Transient
    @ManyToOne(cascade = CascadeType.ALL)
    private Aisle aisleID;



    public TheRow(Long rowID, Square beginSquare, Square endSquare, Aisle aisleID) {
        Preconditions.noneNull(rowID, beginSquare, endSquare, aisleID);
        this.rowID=rowID;
        this.beginSquare = beginSquare;
        this.endSquare=endSquare;
        this.aisleID=aisleID;
    }

    protected TheRow() {
        this.rowID=null;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }


    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.rowID;
    }
}
