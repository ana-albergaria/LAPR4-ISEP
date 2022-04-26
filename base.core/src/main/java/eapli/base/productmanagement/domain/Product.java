package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Product implements AggregateRoot<Code>, Serializable {

    public enum Status {
        AVAILABLE, TEMPORARILY_UNAVAILABLE, UNAVAILABLE;
    }

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    //@AttributeOverride(name="value",column=@Column(name="uniqueInternalCode"))
    //@OneToOne(optional = false, cascade = CascadeType.ALL) //cascade??
    //private Code uniqueInternalCode;
    private Code uniqueInternalCode;
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits."

    private ShortDescription shortDescription;

    private ExtendedDescription extendedDescription;

    private TechnicalDescription technicalDescription; //optional

    private BrandName brandName; //optional

    private Reference reference; //optional

    private Price priceWithoutTaxes;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Weight weight;

    private Volume volume;

    private Price tax;

    private Code productionCode; //optional
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits." OPTIONAL

    @ManyToOne(optional = false)
    private ProductCategory productCategory;

    @ElementCollection
    private Set<Photo> photos; //TENHO DE CRIAR PHOTO - optional

    //+BARCODE

    /**
     * Constructor for Product with the minimum attributes.
     * @param uniqueInternalCode the product unique internal code
     * @param shortDescription the product short description
     * @param extendedDescription the product extended description
     * @param priceWithoutTaxes the product price without taxes
     * @param status the product status
     * @param weight the product weight
     * @param volume the product volume
     * @param tax the product tax
     * @param productCategory the product category
     */
    protected Product(final Code uniqueInternalCode, final ShortDescription shortDescription, final ExtendedDescription extendedDescription,
                      final Price priceWithoutTaxes, final Status status, final Weight weight, final Volume volume,
                      final Price tax, final ProductCategory productCategory){
        Preconditions.noneNull(uniqueInternalCode, shortDescription,extendedDescription,priceWithoutTaxes,status,weight,volume,tax,productCategory);
        this.uniqueInternalCode=uniqueInternalCode;
        this.shortDescription=shortDescription;
        this.extendedDescription=extendedDescription;
        this.priceWithoutTaxes=priceWithoutTaxes;
        this.status=status;
        this.weight=weight;
        this.volume=volume;
        this.tax=tax;
        this.productCategory=null; //TENHO DE APRESENTAR AS CATEGORIAS PARA ESCOLHER
    }

    protected Product(){

    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Code identity() {
        return this.uniqueInternalCode;
    }

    /*
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits."
    public void addProductionCode(final String productionCode) {
        if (!isLetters(productionCode.substring(0, 3))){
            throw new IllegalArgumentException("Production Code needs to start with 4 letters!");
        } else if (productionCode.charAt(4)!='.'){
            throw new IllegalArgumentException("Production Code needs to have a dot as the 5th character!");
        } else if (!isDigits(productionCode.substring(5,9))){
            throw new IllegalArgumentException("Production Code needs to end with 5 digits!");
        }
        this.productionCode = productionCode;
    }

    public boolean isLetters(String string) {
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDigits(String string) {
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    */

}
