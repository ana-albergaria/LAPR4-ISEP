package eapli.base.warehousemanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.model.DomainFactory;

public class AGVBuilder implements DomainFactory<AGV> {
    private AGV theAGV;

    private AgvDock theAGVDock;

    private AutonomyStatus autonomyStatus;

    private TaskStatus taskStatus;

    private Model theModel;

    private String modelID;

    private String shortDescription;

    private Double maxWeight;

    private Square beginSquare;

    private Square endSquare;

    private Square depthSquare;

    private Accessibility accessibilityDirection;

    private AgvDockBuilder agvDockBuilder = new AgvDockBuilder();

    public AGVBuilder withAutonomy(final AutonomyStatus autonomyStatus){
        this.autonomyStatus = autonomyStatus;
        return this;
    }

    public AGVBuilder withTask(final TaskStatus taskStatus){
        this.taskStatus = taskStatus;
        return this;
    }

    public AGVBuilder withModel(final String modelID, final String shortDescription, final Double maxWeight){
        this.theModel = new Model(modelID, shortDescription, maxWeight);
        return this;
    }

    public AGVBuilder withAGVDock(final Square beginSquare, final Square endSquare, final Square depthSquare, final Accessibility accessibilityDirection){
        this.theAGVDock = agvDockBuilder.hasBegin(beginSquare).hasEnd(endSquare).hasDepth(depthSquare).build();
        return this;
    }


    //TODO check about the need to possibly create AGVDockBuilder for beginSquare, endSquare, depthSquare and accessibilityDirection.

    private AGV buildOrThrow() {
        if (theAGV != null) {
            return theAGV;
        } else if (autonomyStatus != null && taskStatus != null && theModel != null && theAGVDock != null) {
            theAGV = new AGV(autonomyStatus, taskStatus, theModel, theAGVDock);
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
