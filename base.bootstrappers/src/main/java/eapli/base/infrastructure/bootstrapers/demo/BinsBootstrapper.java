package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.domain.Bin;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.TheRow;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.base.warehousemanagement.repositories.BinRepository;
import eapli.base.warehousemanagement.repositories.RowRepository;
import eapli.base.warehousemanagement.repositories.ShelfRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

/**
 * @author Marta Ribeiro 1201592
 */
public class BinsBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinsBootstrapper.class);
    private final BinRepository binRepository = PersistenceContext.repositories().bins();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final AisleRepository aisleRepository = PersistenceContext.repositories().aisles();
    private final RowRepository rowRepository = PersistenceContext.repositories().rows();
    private final ShelfRepository shelfRepository = PersistenceContext.repositories().shelfs();

    private Aisle getAisle(final Long id){
        return aisleRepository.ofIdentity(id).orElseThrow(IllegalAccessError::new);
    }

    private TheRow getRow(final Long id){
        return rowRepository.ofIdentity(id).orElseThrow(IllegalAccessError::new);
    }

    private Shelf getShelf(final Long id){
        return shelfRepository.findByID(id).orElseThrow(IllegalAccessError::new);
    }

    private Product getProduct(final Code id){
        return productRepository.ofIdentity(id).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public boolean execute(){

        Double size1 = 0.000665;
        Double size2 = 0.00031;
        Aisle aisle1 = getAisle(1L);
        Aisle aisle2 = getAisle(2L);
        Aisle aisle3 = getAisle(3L);
        Aisle aisle4 = getAisle(4L);
        TheRow row1 = getRow(1L);
        TheRow row2 = getRow(2L);
        TheRow row3 = getRow(3L);
        TheRow row4 = getRow(4L);
        TheRow row5 = getRow(5L);
        TheRow row6 = getRow(6L);
        TheRow row7 = getRow(7L);
        Shelf shelf29 = getShelf(29L);
        Shelf shelf30 = getShelf(30L);
        Shelf shelf31 = getShelf(31L);
        Shelf shelf34 = getShelf(34L);
        Shelf shelf35 = getShelf(35L);
        Shelf shelf36 = getShelf(36L);
        Shelf shelf37 = getShelf(37L);
        Shelf shelf40 = getShelf(40L);
        Shelf shelf41 = getShelf(41L);
        Shelf shelf42 = getShelf(42L);
        Shelf shelf43 = getShelf(43L);
        Shelf shelf44 = getShelf(44L);
        Shelf shelf45 = getShelf(45L);
        Shelf shelf46 = getShelf(46L);
        Shelf shelf47 = getShelf(47L);
        Shelf shelf53 = getShelf(53L);
        Shelf shelf54 = getShelf(54L);
        Shelf shelf55 = getShelf(55L);
        Shelf shelf56 = getShelf(56L);
        Shelf shelf57 = getShelf(57L);
        Shelf shelf60 = getShelf(60L);
        Shelf shelf63 = getShelf(63L);
        Shelf shelf64 = getShelf(64L);
        Shelf shelf65 = getShelf(65L);
        Shelf shelf66 = getShelf(66L);
        Shelf shelf69 = getShelf(69L);
        Shelf shelf70 = getShelf(70L);
        Shelf shelf71 = getShelf(71L);
        Shelf shelf72 = getShelf(72L);
        Shelf shelf73 = getShelf(73L);

        Product product1 = getProduct(Code.valueOf("lmsp.00001"));
        Product product2 = getProduct(Code.valueOf("apsp.00001"));
        Product product3 = getProduct(Code.valueOf("tnfs.00001"));

        register(size1,aisle1,row1,shelf29,product1);
        register(size1,aisle1,row1,shelf30,product1);
        register(size1,aisle1,row1,shelf31,product1);
        register(size1,aisle1,row2,shelf34,product1);
        register(size1,aisle1,row2,shelf35,product1);
        register(size1,aisle1,row2,shelf36,product1);
        register(size1,aisle1,row2,shelf37,product1);
        register(size1,aisle1,row3,shelf40,product1);
        register(size1,aisle1,row3,shelf41,product1);
        register(size1,aisle1,row3,shelf42,product1);

        register(size1,aisle1,row3,shelf43,product2);
        register(size1,aisle1,row3,shelf44,product2);
        register(size1,aisle1,row3,shelf45,product2);
        register(size1,aisle1,row3,shelf46, product2);
        register(size1,aisle1,row3,shelf47,product2);
        register(size1,aisle2,row4,shelf53,product2);
        register(size1,aisle2,row4,shelf54,product2);
        register(size1,aisle2,row4,shelf55,product2);
        register(size1,aisle2,row4,shelf56,product2);
        register(size1,aisle2,row4,shelf57,product2);

        register(size2,aisle2,row5,shelf60,product3);
        register(size2,aisle2,row6,shelf63,product3);
        register(size2,aisle2,row6,shelf64,product3);
        register(size2,aisle2,row6,shelf65,product3);
        register(size2,aisle2,row6,shelf66,product3);
        register(size2,aisle2,row7,shelf69,product3);
        register(size2,aisle2,row7,shelf70,product3);
        register(size2,aisle2,row7,shelf71,product3);
        register(size2,aisle2,row7,shelf72,product3);
        register(size2,aisle2,row7,shelf73,product3);

        return true;
    }

    public void register(Double size, Aisle aisle, TheRow row, Shelf shelf, Product product){
        try{
            final var bin = new Bin(size,aisle,row,shelf,product);
            binRepository.save(bin);
            LOGGER.debug(String.valueOf(aisle), row, shelf);
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            LOGGER.warn("Assuming already exists bin in aisle {} , row {} and shelf {} (see trace log for details on {} {})", aisle, row, shelf,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }

}
