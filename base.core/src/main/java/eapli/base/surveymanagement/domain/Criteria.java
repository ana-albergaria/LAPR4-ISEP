package eapli.base.surveymanagement.domain;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.domain.TheOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;
import javax.persistence.Enumerated;

public enum Criteria{

    GENDER, AGE, PRODUCT_BOUGHT, PRODUCT_CATEGORY_BOUGHT;

    public Long age;

    public enum Signal {
        GREATER_THAN, LESS_THAN;
    }

    @Enumerated
    Signal signal;

    public enum Gender {
        FEMININE, MASCULINE, OTHER;
    }

    @Enumerated
    Gender gender;

    public String productCode;

    public String productCategoryCode;

    public boolean verifyCriteria(Client client){
        if (this.equals(AGE)){
            if (this.signal.equals(Signal.GREATER_THAN)){
                return client.age()>this.age.intValue();
            }else{
                return client.age()<this.age.intValue();
            }
        } else if (this.equals(GENDER)){
            return client.gender().name().equals(this.gender.name());
        } else if (this.equals(PRODUCT_BOUGHT)){
            OrderRepository repository = PersistenceContext.repositories().orders();
            for (TheOrder order:
                    repository.findByClient(client)) {
                for (OrderItem item:
                        order.orderItems()) {
                    if (item.product().getProductCategory().identity().toString().equals(this.productCode)){
                        return true;
                    }
                }
            }
        } else if (this.equals(PRODUCT_CATEGORY_BOUGHT)){
            OrderRepository repository = PersistenceContext.repositories().orders();
            for (TheOrder order:
                    repository.findByClient(client)) {
                for (OrderItem item:
                        order.orderItems()) {
                    if (item.product().identity().code().equals(this.productCategoryCode)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
