package eapli.base.productmanagement.domain;

import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Product {



    @XmlElement
    @EmbeddedId
    //@AttributeOverride(name="value",column=@Column(name="uniqueInternalCode"))
    //@OneToOne(optional = false, cascade = CascadeType.ALL) //cascade??
    //private Code uniqueInternalCode;
    private String uniqueInternalCode;
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits."

    @XmlElement
    private String shortDescription;

    @XmlElement
    private String extendedDescription;

    @XmlElement
    private String technicalDescription;

    @XmlElement
    private String brandName;

    @XmlElement
    private String reference;

    @XmlElement
    private Double priceWithoutTaxes;

    @XmlElement
    private Integer status; //1-Available, 2-Temporarily Unavailable, 3-Unavailable

    @XmlElement
    private Double weight;

    @XmlElement
    private Double volume;

    @XmlElement
    private Double tax;

    @XmlElement
    private String optionalProductionCode;
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits." OR NULL

    @XmlElement
    @ManyToOne(optional = false)
    private ProductCategory productCategory;

    //+PHOTO

    //+BARCODE

    protected Product(final String shortDescription, final String extendedDescription,
                      final String technicalDescription, final String brandName, final String reference,
                      final Double priceWithoutTaxes, final Integer status, final Double weight, final Double volume,
                      final Double tax, final ProductCategory productCategory, final String optionalProductionCode){
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
        this.optionalProductionCode=null; //PODE SER NULL OU SER GERADO
    }

    protected Product(){

    }

}
