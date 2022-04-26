package eapli.base.warehousemanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.model.DomainFactory;

public class AGVBuilder implements DomainFactory<AGV> {
    private AGV theAGV;

    private AgvDock theAGVDock;

    private String autonomyStatus;

    private String taskStatus;

    private String modelID;

    private String shortDescription;

    private Double maxWeight;

    private Square beginSquare;

    private Square endSquare;

    private Square depthSquare;

    private Accessibility accessibilityDirection;

    public AGVBuilder withAutonomy(final String autonomyStatus){
        this.autonomyStatus = autonomyStatus;
        return this;
    }

    public AGVBuilder withTask(final String taskStatus){
        this.taskStatus = taskStatus;
        return this;
    }

    public AGVBuilder withModel(final String modelID){
        this.modelID = modelID;
        return this;
    }

    public AGVBuilder withDescription(final String shortDescription){
        this.shortDescription = shortDescription;
        return this;
    }

    public AGVBuilder withMaxWeight(final Double maxWeight){
        this.maxWeight = maxWeight;
        return this;
    }

    //TODO check about the need to possibly create AGVDockBuilder for beginSquare, endSquare, depthSquare and accessibilityDirection.

    private AGV buildOrThrow() {
        if (theAGV != null) {
            return theAGV;
        } else if (autonomyStatus != null && taskStatus != null && modelID != null && shortDescription != null && maxWeight != null) {
            theAGV = new AGV(autonomyStatus, taskStatus, modelID, shortDescription, maxWeight);
            return theAGV;
        } else {
            throw new IllegalStateException();
        }
    }
    @Override
    public AGV build() {
        final AGV agv = buildOrThrow();
        theAGV = null;
        return agv;
    }
}
