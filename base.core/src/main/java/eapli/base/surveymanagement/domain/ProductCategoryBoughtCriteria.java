package eapli.base.surveymanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;

public class ProductCategoryBoughtCriteria extends Criteria{

    private String code;

    public ProductCategoryBoughtCriteria(final Type type){
        super(type);
    }

    @Override
    public boolean verifyCriteria(Client client) {
        OrderRepository repository = PersistenceContext.repositories().orders();
        for (TheOrder order:
             repository.findByClient(client)) {
            for (OrderItem item:
                    order.orderItems()) {
                if (item.product().identity().code().equals(this.code)){
                    return true;
                }
            }
        }
        return false;
    }
}
