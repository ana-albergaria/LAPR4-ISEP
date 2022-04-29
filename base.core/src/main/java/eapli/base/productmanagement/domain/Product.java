package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Entity
public class Product implements AggregateRoot<Code>, Serializable {

    public void changeTechnicalDescription(TechnicalDescription newTechnicalDescription) {
        Preconditions.nonNull(newTechnicalDescription);
        technicalDescription = newTechnicalDescription;
    }

    public void changeBrandName(BrandName newBrandName) {
        Preconditions.nonNull(newBrandName);
        brandName = newBrandName;

    }

    public void changeReference(Reference newReference) {
        Preconditions.nonNull(newReference);
        reference = newReference;
    }

    public void changeProductionCode(Code newProductionCode) {
        Preconditions.nonNull(newProductionCode);
        productionCode = newProductionCode;
    }

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
    private Code uniqueInternalCode;
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits."

    private ShortDescription shortDescription;

    private ExtendedDescription extendedDescription;

    private TechnicalDescription technicalDescription; //optional

    private BrandName brandName; //optional

    private Reference reference; //optional

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "taxes_currency"))
    })
    private Money priceWithoutTaxes;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Weight weight;

    private Volume volume;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "no_taxes_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "no_taxes_currency"))
    })
    private Money priceWithTaxes;

    @Embedded
    private Code productionCode; //optional
    //"For example, 4 letters followed by a dot (".") and ending with 5 digits." OPTIONAL

    @ManyToOne(optional = false)
    private ProductCategory productCategory;

    @ElementCollection
    private Set<Photo> photos; //TENHO DE CRIAR PHOTO - optional

    private Barcode barcode;

    /**
     * Constructor for Product with the minimum attributes.
     * @param uniqueInternalCode the product unique internal code
     * @param shortDescription the product short description
     * @param extendedDescription the product extended description
     * @param priceWithoutTaxes the product price without taxes
     * @param status the product status
     * @param weight the product weight
     * @param volume the product volume
     * @param priceWithTaxes the product price with taxes
     * @param productCategory the product category
     */
    protected Product(final Code uniqueInternalCode, final ShortDescription shortDescription, final ExtendedDescription extendedDescription,
                      final Money priceWithoutTaxes, final Status status, final Weight weight, final Volume volume,
                      final Money priceWithTaxes, final ProductCategory productCategory){
        Preconditions.noneNull(uniqueInternalCode, shortDescription,extendedDescription,priceWithoutTaxes,status,weight,volume,priceWithTaxes,productCategory);
        this.uniqueInternalCode=uniqueInternalCode;
        this.shortDescription=shortDescription;
        this.extendedDescription=extendedDescription;
        this.priceWithoutTaxes=priceWithoutTaxes;
        this.status=status;
        this.weight=weight;
        this.volume=volume;
        this.priceWithTaxes=priceWithTaxes;
        this.productCategory=null; //TENHO DE APRESENTAR AS CATEGORIAS PARA ESCOLHER
    }

    protected Product(){

    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Product)) {
            return false;
        }

        final var that = (Product) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    //APENAS PARA TESTAR US1004
    public Product(Money priceWithoutTaxes, Weight weight, Volume volume, Money priceWithTaxes) {
        this.priceWithoutTaxes = priceWithoutTaxes;
        this.weight = weight;
        this.volume = volume;
        this.priceWithTaxes = priceWithTaxes;
    }

    public ProductCategory getProductCategory(){
        return productCategory;
    }

    @Override
    public Code identity() {
        return uniqueInternalCode;
    }

    public Code getUniqueInternalCode() {
        return uniqueInternalCode;
    }

    public ShortDescription getShortDescription() {
        return shortDescription;
    }

    public ExtendedDescription getExtendedDescription() {
        return extendedDescription;
    }

    public Optional<TechnicalDescription> getTechnicalDescription() {
        return Optional.ofNullable(technicalDescription);
    }

    public Optional<BrandName> getBrandName() {
        return Optional.ofNullable(brandName);
    }

    public Optional<Reference> getReference() {
        return Optional.ofNullable(reference);
    }

    public Money getPriceWithoutTaxes() {
        return priceWithoutTaxes;
    }

    public Status getStatus() {
        return status;
    }

    public Weight getWeight() {
        return weight;
    }

    public Volume getVolume() {
        return volume;
    }

    public Money getPriceWithTaxes() {
        return priceWithTaxes;
    }

    public Optional<Code> getProductionCode() {
        return Optional.ofNullable(productionCode);
    }

    public Set<Photo> photos() {
        return Collections.unmodifiableSet(photos);
    }

    public boolean addPhoto(final Photo photo){
        return photos.add(photo); //add(new ProductPhoto(photo)) ???
    }

}
