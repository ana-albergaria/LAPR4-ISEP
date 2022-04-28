package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class AgvDock implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private String agvDockID;

    @Transient
    @OneToOne
    private Square beginSquare;

    @Transient
    @OneToOne
    private Square endSquare;

    @Transient
    @OneToOne
    private Square depthSquare;

    @Transient
    @Embedded
    private Accessibility accessibility;

    public AgvDock(String agvDockID, Square beginSquare, Square endSquare, Square depthSquare, Accessibility accessibility){
        Preconditions.noneNull(agvDockID, beginSquare, endSquare, depthSquare, accessibility);
        this.agvDockID=agvDockID;
        this.beginSquare=beginSquare;
        this.endSquare=endSquare;
        this.depthSquare=depthSquare;
        this.accessibility=accessibility;
    }

    protected AgvDock() {
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
        return Long.valueOf(this.agvDockID);
    }

    @Override
    public String toString(){
        return this.agvDockID;
    }
}
