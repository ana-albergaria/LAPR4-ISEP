package eapli.base.warehousemanagement.domain;

import eapli.base.warehousemanagement.domain.Row;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Shelf implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shelfID;

    @ManyToOne
    private Row rowID;


    public Shelf(Row rowID){
        Preconditions.noneNull(rowID);
        this.rowID=rowID;
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
        return this.shelfID;
    }
}
