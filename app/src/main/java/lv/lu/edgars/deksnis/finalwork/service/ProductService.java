package lv.lu.edgars.deksnis.finalwork.service;

import lv.lu.edgars.deksnis.finalwork.model.Product;
import lv.lu.edgars.deksnis.finalwork.model.ProductCategory;
import lv.lu.edgars.deksnis.finalwork.model.ProductData;
import lv.lu.edgars.deksnis.finalwork.model.ProductInputData;
import lv.lu.edgars.deksnis.finalwork.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final Repository<Product> repository;

    @Autowired
    public ProductService(Repository<Product> repository) {
        this.repository = repository;
    }

    public void save(ProductInputData productInputData) {
        Product product = convertFrom(productInputData);
        repository.save(product);
    }

    public List<ProductData> findAll() {
        List<ProductData> result = new ArrayList<>();
        for (Product product : repository.findAll()) {
            result.add(covertFrom(product));
        }
        return result;
    }

    private ProductData covertFrom(Product product) {
        return new ProductData(
                product.getId().toString(),
                product.getName(),
                product.getPrice().toPlainString());
    }

    private Product convertFrom(ProductInputData productInputData) {
        Product product = new Product();
        product.setName(productInputData.getName());
        product.setPrice(BigDecimal.valueOf(productInputData.getPrice()));
        product.setCategory(ProductCategory.valueOf(productInputData.getCategory()));
        if (productInputData.getDiscount() != null) {
            product.setDiscount(BigDecimal.valueOf(productInputData.getDiscount()));
        }
        if (productInputData.getDescription() != null) {
            product.setDescription(productInputData.getDescription());
        }
        return product;
    }
}

