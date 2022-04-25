package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.DomainFactory;

public class ProductBuilder implements DomainFactory<Product> {

    private Product theProduct;

    //+atributos obrigatorios

    private Product buildOrThrow(){
        if (theProduct!=null){
            return theProduct;
        } else if (true/*obrigatorios nao null*/){
            theProduct = new Product(/*atributos obrigatorios*/);
            return theProduct;
        } else {
            throw new IllegalStateException();
        }
    }

    //+atributos opcionais

    @Override
    public Product build() {
        final Product ret = buildOrThrow();
        theProduct = null;
        return ret;
    }

}
