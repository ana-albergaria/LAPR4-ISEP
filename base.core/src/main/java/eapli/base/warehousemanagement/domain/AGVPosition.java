package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class AGVPosition implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PositionSquareID", referencedColumnName = "id")
    private Square positionSquare;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AGVID", referencedColumnName = "agvID")
    private AGV agv;

    public AGVPosition(Square positionSquare, AGV agv){
        Preconditions.noneNull(positionSquare, agv);
        this.positionSquare=positionSquare;
        this.agv=agv;
    }

    public AGVPosition() {}

    public Long getAGVID(){
        return this.agv.getAgvID();
    }

    public Long getLSquare(){
        return this.positionSquare.lSquare();
    }

    public Long getWSquare() {
        return this.positionSquare.wSquare();
    }

    public static AGVPosition valueOf(final Square positionSquare, final AGV agv){

        return new AGVPosition(positionSquare, agv);
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AGVPosition)) {
            return false;
        } else {
            AGVPosition that = (AGVPosition) o;
            return this.id.equals(that.id);
        }
    }

    public int hashCode() {
        return (new HashCoder()).with(this.id).code();
    }

    public String toString() {
        return String.format("AGV Position: %d, %d \n", positionSquare.lSquare(), positionSquare.wSquare());
    }

    public int compareTo(final AGVPosition o) {
        return this.id.compareTo(o.id);
    }
}
