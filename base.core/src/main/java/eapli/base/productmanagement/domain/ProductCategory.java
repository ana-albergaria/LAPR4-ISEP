package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Entity
public class ProductCategory implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private String alphanumericCode;

    private String description;

    protected ProductCategory(){

    }

    public ProductCategory(final String alphanumericCode, final String description){
        Preconditions.noneNull(alphanumericCode,description);
        this.alphanumericCode=alphanumericCode;
        this.description=description;
    }

    public String getAlphanumericCode() {
        return alphanumericCode;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.alphanumericCode;
    }
}