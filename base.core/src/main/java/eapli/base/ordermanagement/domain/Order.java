package eapli.base.ordermanagement.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Order {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long orderId;

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;
}
