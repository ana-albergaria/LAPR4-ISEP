package eapli.base.productmanagement.domain;

import eapli.base.clientmanagement.domain.Address;
import eapli.base.clientmanagement.domain.Client;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
@Entity
public class Product {

    public enum Status {
        AVAILABLE, TEMPORARILY_UNAVAILABLE, UNAVAILABLE;
    }

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    //@AttributeOverride(name="value",column=@Column(name="uniqueInternalCode"))
    //@OneToOne(optional = false, cascade = CascadeType.ALL) //cascade??
    //private Code uniqueInternalCode;
    private String uniqueInternalCode;
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits."

    private String shortDescription;

    private String extendedDescription;

    private String technicalDescription;

    private String brandName;

    private String reference;

    private Double priceWithoutTaxes;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double weight;

    private Double volume;

    private Double tax;

    private String productionCode;
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits." OPTIONAL

    @ManyToOne(optional = false)
    private ProductCategory productCategory;

    //@ElementCollection
    //private Set<Photo> photos; //TENHO DE CRIAR PHOTO

    //+BARCODE

    /**
     * Constructor for Product with the minimum attributes.
     * @param shortDescription the product short description
     * @param extendedDescription the product extended description
     * @param technicalDescription the product technical description
     * @param brandName the product brand name
     * @param reference the product reference
     * @param priceWithoutTaxes the product price without taxes
     * @param status the product status
     * @param weight the product weight
     * @param volume the product volume
     * @param tax the product tax
     * @param productCategory the product category
     */
    protected Product(final String shortDescription, final String extendedDescription,
                      final String technicalDescription, final String brandName, final String reference,
                      final Double priceWithoutTaxes, final Status status, final Double weight, final Double volume,
                      final Double tax, final ProductCategory productCategory){
        Preconditions.noneNull(shortDescription,extendedDescription,technicalDescription,brandName,reference,priceWithoutTaxes,status,weight,volume,tax,productCategory); //o optionalProductionCode não está incluido
        this.uniqueInternalCode=null; //TEM DE SER GERADO
        this.shortDescription=shortDescription;
        this.extendedDescription=extendedDescription;
        this.technicalDescription=technicalDescription;
        this.brandName=brandName;
        this.reference=reference;
        this.priceWithoutTaxes=priceWithoutTaxes;
        this.status=status;
        this.weight=weight;
        this.volume=volume;
        this.tax=tax;
        this.productCategory=null; //TENHO DE APRESENTAR AS CATEGORIAS PARA ESCOLHER
        //1 or + photos
    }

    //+productionCode

    protected Product(){

    }

    //"For example, 4 letters followed by a dot (".") and ending with 5 digits." OPTIONAL
    public void addProductionCode(final String productionCode) {
        if (productionCode == null) { //ou nao está no formato certo
            throw new IllegalArgumentException();
        }
        this.productionCode = productionCode;
    }

}
