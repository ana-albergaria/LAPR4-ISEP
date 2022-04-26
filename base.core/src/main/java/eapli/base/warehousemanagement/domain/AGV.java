package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AGV implements AggregateRoot<Long> {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long agvID;

    private String autonomyStatus;

    private String taskStatus;

    private String modelID;

    private String shortDescription;

    private Double maxWeight;

    public AGV(final String autonomyStatus, final String taskStatus, final String modelID, final String shortDescription, final Double maxWeight){
        Preconditions.noneNull(autonomyStatus, taskStatus, modelID, shortDescription, maxWeight);
        this.autonomyStatus = autonomyStatus;
        this.taskStatus = taskStatus;
        this.modelID = modelID;
        this.shortDescription = shortDescription;
        this.maxWeight = maxWeight;
    }

    protected AGV(){

    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(Long other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public Long identity() {
        return null;
    }

    @Override
    public boolean hasIdentity(Long id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}
