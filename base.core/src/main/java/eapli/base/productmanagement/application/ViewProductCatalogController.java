package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ViewProductCatalogController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    /*public List<Product> showProductCatalogWithOneOrMoreFilters(Map<Integer, String> filters){
        String queryStatement = "";
        if(filters.size() == 1){
            for(Integer filter : filters.keySet()){
                String filterName = filters.get(filter);
                if(filterName.equalsIgnoreCase("BRANDNAME")) {
                    queryStatement = "WHERE BRANDNAME = '" + filterName.toUpperCase() + "';";
                }else if(filterName.equalsIgnoreCase("")){

                }
                productRepository.query(, queryStatement);
            }
        }else if(filters.size() > 1){

        }
    }*/

    public List<String> showProductCatalog(){
        List<String> productsInfo = new LinkedList<>();

        for(Product product : productRepository.findAll()){
            productsInfo.add(product.toString());
        }

        return productsInfo;
    }
}
