package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Row implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private Long rowID;


    @OneToOne
    @JoinColumn(name = "begin_square_id")
    private Square beginSquare;

    @OneToOne
    @JoinColumn(name = "end_square_id")
    private Square endSquare;


    @ManyToOne
    private Aisle aisleID;



    public Row(Long rowID, Square beginSquare, Square endSquare, Aisle aisleID) {
        Preconditions.noneNull(rowID, beginSquare, endSquare, aisleID);
        this.rowID=rowID;
        this.beginSquare = beginSquare;
        this.endSquare=endSquare;
        this.aisleID=aisleID;
    }

    protected Row() {
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
