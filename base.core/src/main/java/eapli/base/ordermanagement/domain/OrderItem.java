package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import java.io.Serializable;

//VALUE OBJECT OU ENTITY?
public class OrderItem implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private final int quantity;
    private final Product item;

    public OrderItem(final int qty, final Product item) {
        Preconditions.isPositive(qty);
        Preconditions.nonNull(item);

        quantity = qty;
        this.item = item;
    }

    public Product product() {
        return item;
    }

    public int quantity() {
        return quantity;
    }

}
