package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Money;

import java.util.Set;

public class ProductBuilder implements DomainFactory<Product> {

    private Product theProduct;

    private Code uniqueInternalCode;

    private Barcode barcode;

    private ShortDescription shortDescription;

    private ExtendedDescription extendedDescription;

    private Money priceWithoutTaxes;

    private Product.Status status;

    private Weight weight;

    private Volume volume;

    private Money priceWithTaxes;

    private ProductCategory category;

    //atributos obrigatorios

    public ProductBuilder ofCategory(final ProductCategory productCategory){
        category=productCategory;
        return this;
    }

    public ProductBuilder shortlyDescriptedAs(final String shortDescription){
        return shortlyDescriptedAs(ShortDescription.valueOf(shortDescription));
    }

    public ProductBuilder shortlyDescriptedAs(final ShortDescription shortDescription){
        this.shortDescription=shortDescription;
        return this;
    }

    public ProductBuilder extendedlyDescriptedAs(final String extendedDescription){
        return shortlyDescriptedAs(ShortDescription.valueOf(extendedDescription));
    }

    public ProductBuilder extendedlyDescriptedAs(final ExtendedDescription extendedDescription){
        this.extendedDescription=extendedDescription;
        return this;
    }

    public ProductBuilder codedAs(final String uniqueInternalCode){
        return codedAs(Code.valueOf(uniqueInternalCode));
    }

    public ProductBuilder codedAs(final Code uniqueInternalCode){
        this.uniqueInternalCode=uniqueInternalCode;
        return this;
    }

    public ProductBuilder ofBarcode(final String barcode){
        return ofBarcode(Barcode.valueOf(barcode));
    }

    public ProductBuilder ofBarcode(final Barcode barcode){
        this.barcode=barcode;
        return this;
    }

    public ProductBuilder initialyPricedAs(final Money priceWithoutTaxes){
        this.priceWithoutTaxes=priceWithoutTaxes;
        return this;
    }

    public ProductBuilder statusAs(final String status){
        return statusAs(Product.Status.valueOf(status));
    }

    public ProductBuilder statusAs(final Product.Status status){
        this.status=status;
        return this;
    }

    public ProductBuilder weightAs(final Weight weight){
        this.weight=weight;
        return this;
    }

    public ProductBuilder volumeAs(final Volume volume){
        this.volume=volume;
        return this;
    }

    public ProductBuilder afterTaxesPricedAs(final Money priceWithTaxes){
        this.priceWithTaxes=priceWithTaxes;
        return this;
    }

    private Product buildOrThrow(){
        // we will create the actual instance inside the builder during the building process, but
        // that is hidden from the client code. conceptually, the client code only sees the new
        // instance (it is only built) in the build method
        if (theProduct!=null){
            return theProduct;
        } else if (category!=null && shortDescription!=null && extendedDescription!=null &&
        priceWithoutTaxes!=null && status!=null && weight!=null && volume!=null && priceWithTaxes!=null){
            theProduct = new Product(uniqueInternalCode, shortDescription, extendedDescription,
                    priceWithoutTaxes, status, weight, volume, priceWithTaxes, category);
            return theProduct;
        } else {
            throw new IllegalStateException();
        }
    }

    //atributos opcionais

    public ProductBuilder withTechnicalDescription(final TechnicalDescription technicalDescription){
        buildOrThrow();
        theProduct.changeTechnicalDescription(technicalDescription);
        return this;
    }

    public ProductBuilder withBrandName(final BrandName brandName){
        buildOrThrow();
        theProduct.changeBrandName(brandName);
        return this;
    }

    public ProductBuilder withReference(final Reference reference){
        buildOrThrow();
        theProduct.changeReference(reference);
        return this;
    }

    public ProductBuilder withProductionCode(final Code productionCode){
        buildOrThrow();
        theProduct.changeProductionCode(productionCode);
        return this;
    }

    public ProductBuilder withPhotos(final Set<Photo> photos){
        if (photos != null) {
            photos.forEach(this::withPhoto);
        }
        return this;
    }

    public ProductBuilder withPhoto(final Photo photo){
        buildOrThrow();
        theProduct.addPhoto(photo);
        return this;
    }

    @Override
    public Product build() {
        final Product ret = buildOrThrow();
        theProduct = null;
        return ret;
    }

}
