package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class PlantBuilder implements DomainFactory<WarehousePlant> {
    private WarehousePlant plant;

    private String warehouseName;
    private int length;
    private int width;
    private int squareSize;
    private String unit;

    public PlantBuilder hasName(String warehouseName){
        this.warehouseName=warehouseName;
        return this;
    }

    public PlantBuilder hasLength(int length){
        this.length=length;
        return this;
    }

    public PlantBuilder hasWidth(int width){
        this.width=width;
        return this;
    }

    public PlantBuilder hasSquareSize(int squareSize){
        this.squareSize=squareSize;
        return this;
    }

    public PlantBuilder hasUnit(String unit){
        this.unit=unit;
        return this;
    }

    private WarehousePlant buildOrThrow(){
        if (plant!=null) {
            return plant;
        } else
            //plant = new WarehousePlant(warehouseName, length, width, squareSize, unit);

        return null;
    }

    @Override
    public WarehousePlant build() {
        final WarehousePlant fin = buildOrThrow();
        plant = null;
        return fin;
    }
}
