package eapli.base.warehousemanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class ShelfBuilder implements DomainFactory<Shelf> {
    private Shelf shelf;

    private Row rowID;

    public ShelfBuilder hasRow(Row rowID){
        this.rowID=rowID;
        return this;
    }

    private Shelf buildOrThrow(){
        if (shelf!=null) {
            return shelf;
        } else if (rowID!=null ) {
            return shelf = new Shelf(rowID);
        }else
            throw new IllegalStateException();
    }
    @Override
    public Shelf build() {
        final Shelf fin = buildOrThrow();
        shelf = null;
        return fin;
    }
}
