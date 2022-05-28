package eapli.base.ordermanagement.domain;

import eapli.base.clientmanagement.domain.*;
import eapli.base.clientmanagement.domain.Name;
import eapli.base.productmanagement.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.time.util.Calendars;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class TheTaskTest {
    private OrderAddress billingAddress;
    private OrderAddress shippingAdddress;
    private Client client;
    private Set<Address> addresses = new HashSet<>();
    private SystemUser salesClerk;
    private Set<Role> roles = new HashSet<>();
    private List<OrderItem> newOrderItems = new ArrayList<>();
    private OrderItem orderItem1;
    private TheOrder order;
    private Square beginSquare;
    private Square endSquare;
    private Square depthSquare;
    private Accessibility accessibility;
    private AgvDock agvDock;
    private AutonomyStatus autonomyStatus;
    private TaskStatus taskStatus;
    private AGV agv;


    @Before
    public void setUp() {
        billingAddress = new OrderAddress("Travessa do Outeiro 1","7","4520-463","Rio Meão","Portugal");
        shippingAdddress = new OrderAddress("Travessa do Outeiro 1","7","4520-463","Rio Meão","Portugal");
        addresses.add(Address.valueOf("Travessa do Outeiro 1","7","4520-463","Rio Meão","Portugal"));
        client = new Client(Name.valueOf("Joao", "Sousa"), VAT.valueOf("PT123456789"), Email.valueOf("joao@gmail.com"), PhoneNumber.valueOf("+351918413784"), addresses);
        roles.add(BaseRoles.SALES_CLERK);
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        salesClerk = userBuilder.with("sales_clerk", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
        orderItem1 = new OrderItem(3,new Product(Code.valueOf("abcd.12345"), Barcode.valueOf("123456789012"), ShortDescription.valueOf("Short description."), ExtendedDescription.valueOf("Very very very very very very very extended description."),
                Money.euros(1.5), Product.Status.AVAILABLE,Weight.valueOf(10),Volume.valueOf(5.3),Money.euros(3),new ProductCategory(AlphaNumericCode.valueOf("code"),CategoryDescription.valueOf("this is the description of a category"))));
        newOrderItems.add(orderItem1);

        order = new TheOrder(client,billingAddress,shippingAdddress,Shipment.BLUE,Payment.APPLE_PAY, TheOrder.SourceChannel.CALL, Calendars.now(),salesClerk,newOrderItems);


        beginSquare = new Square(1L, 1L);
        endSquare = new Square(1L, 6L);
        depthSquare = new Square(2L, 4L);
        accessibility = new Accessibility("right");
        agvDock = new AgvDock("165", beginSquare, endSquare, depthSquare, accessibility);
        autonomyStatus = new AutonomyStatus("2h");
        taskStatus = new TaskStatus(TaskStatus.TaskStatusEnum.FREE);
        agv = new AGV(1234L, autonomyStatus, taskStatus, "1h23", "test", 22.3, agvDock);
    }

    @Test
    public void ensureTaskIsCreated(){
        TheTask task = new TheTask(agv, order);
        assertNotNull(task);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskAGVNotNull(){
        TheTask task = new TheTask(null, order);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureTaskOrderNotNull(){
        TheTask task = new TheTask(agv, null);
    }

}
