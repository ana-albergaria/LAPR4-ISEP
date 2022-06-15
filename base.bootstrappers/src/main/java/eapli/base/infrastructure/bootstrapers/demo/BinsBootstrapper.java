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
        Shelf shelf32 = getShelf(32L);
        Shelf shelf33 = getShelf(33L);
        Shelf shelf34 = getShelf(34L);
        Shelf shelf37 = getShelf(37L);
        Shelf shelf38 = getShelf(38L);
        Shelf shelf39 = getShelf(39L);
        Shelf shelf40 = getShelf(40L);
        Shelf shelf43 = getShelf(43L);
        Shelf shelf44 = getShelf(44L);
        Shelf shelf45 = getShelf(45L);
        Shelf shelf46 = getShelf(46L);
        Shelf shelf47 = getShelf(47L);
        Shelf shelf48 = getShelf(48L);
        Shelf shelf49 = getShelf(49L);
        Shelf shelf50 = getShelf(50L);
        Shelf shelf59 = getShelf(59L);
        Shelf shelf60 = getShelf(60L);
        Shelf shelf61 = getShelf(61L);
        Shelf shelf62 = getShelf(62L);
        Shelf shelf63 = getShelf(63L);
        Shelf shelf66 = getShelf(66L);
        Shelf shelf69 = getShelf(69L);
        Shelf shelf70 = getShelf(70L);
        Shelf shelf71 = getShelf(71L);
        Shelf shelf72 = getShelf(72L);
        Shelf shelf75 = getShelf(75L);
        Shelf shelf76 = getShelf(76L);
        Shelf shelf77 = getShelf(77L);
        Shelf shelf78 = getShelf(78L);
        Shelf shelf79 = getShelf(79L);

        Product product1 = getProduct(Code.valueOf("lmsp.00001"));
        Product product2 = getProduct(Code.valueOf("apsp.00001"));
        Product product3 = getProduct(Code.valueOf("tnfs.00001"));

        register(size1,aisle1,row1,shelf32,product1);
        register(size1,aisle1,row1,shelf33,product1);
        register(size1,aisle1,row1,shelf34,product1);
        register(size1,aisle1,row2,shelf37,product1);
        register(size1,aisle1,row2,shelf38,product1);
        register(size1,aisle1,row2,shelf39,product1);
        register(size1,aisle1,row2,shelf40,product1);
        register(size1,aisle1,row3,shelf43,product1);
        register(size1,aisle1,row3,shelf44,product1);
        register(size1,aisle1,row3,shelf45,product1);

        register(size1,aisle1,row3,shelf46,product2);
        register(size1,aisle1,row3,shelf47,product2);
        register(size1,aisle1,row3,shelf48,product2);
        register(size1,aisle1,row3,shelf49, product2);
        register(size1,aisle1,row3,shelf50,product2);
        register(size1,aisle2,row4,shelf59,product2);
        register(size1,aisle2,row4,shelf60,product2);
        register(size1,aisle2,row4,shelf61,product2);
        register(size1,aisle2,row4,shelf62,product2);
        register(size1,aisle2,row4,shelf63,product2);

        register(size2,aisle2,row5,shelf66,product3);
        register(size2,aisle2,row6,shelf69,product3);
        register(size2,aisle2,row6,shelf70,product3);
        register(size2,aisle2,row6,shelf71,product3);
        register(size2,aisle2,row6,shelf72,product3);
        register(size2,aisle2,row7,shelf75,product3);
        register(size2,aisle2,row7,shelf76,product3);
        register(size2,aisle2,row7,shelf77,product3);
        register(size2,aisle2,row7,shelf78,product3);
        register(size2,aisle2,row7,shelf79,product3);

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