package eapli.base.utils;

import eapli.base.warehousemanagement.domain.*;

public class CreateWarehouseMatrix {

    public static int[][] createAccordingWithSize(WarehousePlant plant) {
        Length warehouseLength = plant.warehouseLength();
        Width warehouseWidth = plant.warehouseWidth();
        int[][] warehouseMatrix = new int[Math.toIntExact(warehouseWidth.width())][Math.toIntExact(warehouseLength.length())];

        for (int i=0; i< warehouseMatrix.length; i++){
            for (int j=0; j<warehouseMatrix[0].length; j++){
                warehouseMatrix[i][j] = 0;
            }
        }

        return warehouseMatrix;
    }

    public static void insertObstacles(int[][] warehouseMatrix, Iterable<AgvDock> docks, Iterable<Aisle> aisles){
        int beginW, beginL, endW, endL, depthW, depthL;
        for (AgvDock dock : docks){
            Square beginSquare = dock.beginSquare();
            Square endSquare = dock.endSquare();
            Square depthSquare = dock.depthSquare();
            beginW = Math.toIntExact(beginSquare.wSquare())-1;
            beginL = Math.toIntExact(beginSquare.lSquare())-1;
            endL = Math.toIntExact(endSquare.lSquare())-1;
            depthW = Math.toIntExact(depthSquare.wSquare())-1;
            for(int i= beginW; i<= depthW; i++){
                for(int j = beginL; j<= endL; j++){
                    warehouseMatrix[i][j] = 1;
                }
            }
        }

        for(Aisle aisle: aisles){
            Square beginSquare = aisle.beginSquare();
            Square endSquare = aisle.endSquare();
            Square depthSquare = aisle.depthSquare();
            beginW = Math.toIntExact(beginSquare.wSquare())-1;
            beginL = Math.toIntExact(beginSquare.lSquare())-1;
            endL = Math.toIntExact(endSquare.lSquare())-1;
            depthW = Math.toIntExact(depthSquare.wSquare())-1;
            for(int i= beginW; i<= depthW; i++){
                for(int j = beginL; j<= endL; j++){
                    warehouseMatrix[i][j] = 1;
                }
            }
        }
    }
}
