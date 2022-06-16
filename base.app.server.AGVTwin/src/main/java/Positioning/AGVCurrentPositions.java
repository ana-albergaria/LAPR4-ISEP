package Positioning;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.AGVPosition;
import eapli.base.warehousemanagement.domain.Square;
import eapli.base.warehousemanagement.repositories.AgvPositionRepository;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class AGVCurrentPositions {
    private static Double speed;
    private static String[][] warehousePlant;
    private static AGV currentAGV;
    private static LinkedList<Point2D> thisRoute;
    private static int timeSpent;


    public AGVCurrentPositions (Double currentSpeed, String[][] matrix, AGV agv, LinkedList<Point2D> route, int time) {
        speed=currentSpeed;
        warehousePlant=matrix;
        currentAGV=agv;
        thisRoute = route;
        timeSpent=time;
    }

    public static synchronized AGVPosition calculatePosition() {
        AGVPosition currentPosition=null;
        int nextIndex = (int) (speed * timeSpent);
        int index;
        Point2D nextPos;
        long wsquare, lsquare;


        for(int i=0; i< warehousePlant.length; i++){
            for (int j=0; j< warehousePlant[0].length; j++){
                if (warehousePlant[i][j].equalsIgnoreCase(String.valueOf(currentAGV.getAgvID()))){
                    index = thisRoute.indexOf(new Point2D.Double((long) j, (long) i));
                    nextPos = thisRoute.get(index+nextIndex);
                    wsquare = Double.valueOf(nextPos.getX()).longValue();
                    lsquare = Double.valueOf(nextPos.getY()).longValue();
                    currentPosition = new AGVPosition(new Square(wsquare, lsquare), currentAGV);
                }
            }
        }

        if(currentPosition==null){
            index = thisRoute.indexOf(new Point2D.Double(0L, 0L));
            nextPos = thisRoute.get(index+nextIndex);
            wsquare = Double.valueOf(nextPos.getX()).longValue();
            lsquare = Double.valueOf(nextPos.getY()).longValue();
            currentPosition = new AGVPosition(new Square(wsquare, lsquare), currentAGV);
        }

        return currentPosition;
    }
}
