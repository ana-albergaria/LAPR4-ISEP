package eapli.base.clientmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Entity
public class Client implements AggregateRoot<Long>, Serializable {

    public enum Gender {
        FEMININE, MASCULINE, OTHER;
    }

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long clientId;

    private Name name;

    private VAT vat;

    private Email email;

    private PhoneNumber phoneNumber;

    @Temporal(TemporalType.DATE)
    private Calendar birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection
    private Set<Address> addresses;


    /**
     * Constructor for Client with the minimum attributes.
     *
     * @param name the costumer name
     * @param vat the costumer vat
     * @param email the costumer email
     * @param phoneNumber the costumer phone number
     */
    public Client(final Name name, final VAT vat, final Email email, final PhoneNumber phoneNumber, final Set<Address> addresses) {
        Preconditions.noneNull(name, vat, email, phoneNumber);
        Preconditions.noneNull(addresses, "The Client must have at least one address.");
        //usar o Preconditions.ensure para fazer verificações à data
        this.name = name;
        this.vat = vat;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
    }

    protected Client() {
        //for ORM only
    }

    public void addGender(final Gender gender) {
        this.gender = gender;
    }

    public void insertBirthDate(final Calendar birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.clientId;
    }

}
