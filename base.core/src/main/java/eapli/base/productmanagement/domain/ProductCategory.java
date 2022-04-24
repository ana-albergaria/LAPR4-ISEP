package eapli.base.productmanagement.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class ProductCategory {

    @XmlElement
    @EmbeddedId
    private String alphanumericCode;

    @XmlElement
    private String description;

    protected ProductCategory(){

    }

    public ProductCategory(final String alphanumericCode, final String description){
        Preconditions.noneNull(alphanumericCode,description);
        this.alphanumericCode=alphanumericCode;
        this.description=description;
    }

}
